package team.msg.sms.domain.authentication.usecase

import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.authentication.dto.res.StudentAuthenticationFormResponseData
import team.msg.sms.domain.authentication.model.*
import team.msg.sms.domain.authentication.service.*
import java.util.UUID

@UseCase
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

        // 채점 전 상태인 학생 폼을 조회할시 채점 중으로 상태 변경
        if(markingBoard.markingBoardType == MarkingBoardType.PENDING_REVIEW) {
            markingBoardService.save(
                markingBoard.copy(
                    id = markingBoard.id,
                    markingBoardType = MarkingBoardType.UNDER_REVIEW
                )
            )
        }

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