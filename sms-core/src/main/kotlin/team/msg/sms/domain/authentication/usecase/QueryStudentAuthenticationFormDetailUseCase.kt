package team.msg.sms.domain.authentication.usecase

import team.msg.sms.common.annotation.ReadOnlyUseCase
import team.msg.sms.domain.authentication.dto.res.StudentAuthenticationFormResponseData
import team.msg.sms.domain.authentication.model.AuthenticationArea
import team.msg.sms.domain.authentication.model.AuthenticationSection
import team.msg.sms.domain.authentication.model.SelectorSectionValue
import team.msg.sms.domain.authentication.model.UserFormValue
import team.msg.sms.domain.authentication.service.*
import java.util.UUID

@ReadOnlyUseCase
class QueryStudentAuthenticationFormDetailUseCase(
    private val markingBoardService: MarkingBoardService,
    private val authenticationAreaService: AuthenticationAreaService,
    private val authenticationSectionService: AuthenticationSectionService,
    private val authenticationFieldGroupService: AuthenticationFieldGroupService,
    private val authenticationFieldService: AuthenticationFieldService,
    private val userFormValueService: UserFormValueService,
    private val selectorSectionValueService: SelectorSectionValueService
) {
    fun execute(markingBoardId: UUID): StudentAuthenticationFormResponseData {
        val markingBoard = markingBoardService.getMarkingBoardById(id = markingBoardId)

        val authenticationAreas =
            authenticationAreaService.getGroupAuthenticationAreaByAuthenticationFormId(markingBoard.authenticationId)

        val authenticationSections =
            authenticationSectionService.getAuthenticationSectionByGroupIds(authenticationAreas.map { it.id })

        val selectorSectionValues = selectorSectionValueService.getSelectorSectionValue()

        return StudentAuthenticationFormResponseData(
            markingBoardId = markingBoard.id,
            title = markingBoard.title,
            content = buildAreas(
                authenticationAreas,
                authenticationSections,
                selectorSectionValues,
                markingBoard.studentId
            )
        )
    }
    private fun buildAreas(
        authenticationAreas: List<AuthenticationArea>,
        authenticationSections: List<AuthenticationSection>,
        selectorSectionValues: List<SelectorSectionValue>,
        studentId: UUID
    ): List<StudentAuthenticationFormResponseData.Area> {
        return authenticationAreas.map { area ->
            StudentAuthenticationFormResponseData.Area(
                areaId = area.id,
                areaTitle = area.title,
                sections = buildSections(area.id, authenticationSections, selectorSectionValues, studentId)
            )
        }
    }

    private fun buildSections(
        areaId: UUID,
        authenticationSections: List<AuthenticationSection>,
        selectorSectionValues: List<SelectorSectionValue>,
        studentId: UUID
    ): List<StudentAuthenticationFormResponseData.Section> {
        return authenticationSections.filter { it.groupId == areaId }
            .map { section ->
                StudentAuthenticationFormResponseData.Section(
                    sectionId = section.id,
                    sectionName = section.sectionName,
                    maxCount = section.maxCount,
                    groups = buildGroups(section.id, selectorSectionValues, studentId)
                )
            }
    }

    private fun buildGroups(
        sectionId: UUID,
        selectorSectionValues: List<SelectorSectionValue>,
        studentId: UUID
    ): List<StudentAuthenticationFormResponseData.Group> {
        return authenticationFieldGroupService.findAuthenticationFieldGroupBySectionId(sectionId)
            .map { fieldGroup ->
                StudentAuthenticationFormResponseData.Group(
                    groupId = fieldGroup.id,
                    maxScore = fieldGroup.maxScore,
                    fields = buildFieldSets(fieldGroup.id, selectorSectionValues, studentId)
                )
            }
    }

    private fun buildFieldSets(
        groupId: UUID,
        selectorSectionValues: List<SelectorSectionValue>,
        studentId: UUID
    ): List<StudentAuthenticationFormResponseData.FieldSet> {
        return authenticationFieldService.getAuthenticationFieldsByGroupId(groupId)
            .flatMap { authenticationField ->
                val userFormValues =
                    userFormValueService.getUserFormValueListByFieldIdAndStudentId(authenticationField.id, studentId)
                val setIds = userFormValues.map { it.setId }.distinct()
                setIds.map { setId ->
                    StudentAuthenticationFormResponseData.FieldSet(
                        setId = setId,
                        values = buildFields(userFormValues, setId, selectorSectionValues)
                    )
                }
            }
    }

    private fun buildFields(
        userFormValues: List<UserFormValue>,
        setId: UUID,
        selectorSectionValues: List<SelectorSectionValue>
    ): List<StudentAuthenticationFormResponseData.Field> {
        return userFormValues.filter { it.setId == setId }
            .map { userFormValue ->
                StudentAuthenticationFormResponseData.Field(
                    fieldId = userFormValue.authenticationFieldId,
                    value = userFormValue.targetId?.let { targetId ->
                        selectorSectionValues.find { it.id == targetId }?.name
                    } ?: userFormValue.value
                )
            }
    }
}