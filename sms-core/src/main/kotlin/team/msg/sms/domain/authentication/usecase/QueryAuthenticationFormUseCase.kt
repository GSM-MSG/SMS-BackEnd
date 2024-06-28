package team.msg.sms.domain.authentication.usecase

import org.springframework.transaction.annotation.Transactional
import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.authentication.dto.res.*
import team.msg.sms.domain.authentication.model.AuthenticationSection
import team.msg.sms.domain.authentication.model.AuthenticationArea
import team.msg.sms.domain.authentication.model.FieldType
import team.msg.sms.domain.authentication.model.SelectorSectionValue
import team.msg.sms.domain.authentication.service.*
import team.msg.sms.domain.file.dto.res.FileResponseData
import team.msg.sms.domain.file.service.FileService
import java.util.*
import kotlin.math.max

@UseCase
class QueryAuthenticationFormUseCase(
    private val authenticationSectionService: AuthenticationSectionService,
    private val fileService: FileService,
    private val selectorSectionValueService: SelectorSectionValueService,
    private val authenticationFieldService: AuthenticationFieldService,
    private val authenticationAreaService: AuthenticationAreaService,
    private val authenticationFieldGroupService: AuthenticationFieldGroupService
) {
    @Transactional(readOnly = true)
    fun execute(authenticationFormId: UUID): QueryAuthenticationFormResponseData {
        val files = fetchFiles(authenticationFormId)
        val groups = fetchAuthenticationGroups(authenticationFormId)
        val authenticationSections = fetchAuthenticationSections(groups)
        val selectorSectionValues = selectorSectionValueService.getSelectorSectionValue()

        return buildQueryResponse(groups, files, authenticationSections, selectorSectionValues)
    }

    private fun fetchAuthenticationGroups(authenticationFormId: UUID): List<AuthenticationArea> {
        return authenticationAreaService.getGroupAuthenticationAreaByAuthenticationFormId(authenticationFormId)
    }

    private fun fetchFiles(authenticationFormId: UUID): List<FileResponseData> {
        return fileService.getFileByTargetUuidAndTypeEqualsAuthentication(authenticationFormId).map { file ->
            FileResponseData(
                name = file.fileName,
                url = file.fileUrl
            )
        }
    }

    private fun fetchAuthenticationSections(groups: List<AuthenticationArea>): List<AuthenticationSection> {
        val groupIds = groups.map { it.id }
        return authenticationSectionService.getAuthenticationSectionByGroupIds(groupIds)
    }

    private fun buildQueryResponse(
        groups: List<AuthenticationArea>,
        files: List<FileResponseData>,
        authenticationSections: List<AuthenticationSection>,
        selectorSectionValues: List<SelectorSectionValue>
    ): QueryAuthenticationFormResponseData {
        return QueryAuthenticationFormResponseData(
            files = files,
            content = groups.map { group ->
                AuthenticationAreaFormResponseData(
                    title = group.title,
                    sections = filterSections(authenticationSections, group.id).map { section ->
                        AuthenticationSectionResponseData(
                            sectionId = section.id,
                            sectionName = section.sectionName,
                            maxCount = section.maxCount,
                            groups = buildFieldGroups(section.id, selectorSectionValues)
                        )
                    }
                )
            }
        )
    }

    private fun filterSections(authenticationSections: List<AuthenticationSection>, areaId: UUID): List<AuthenticationSection> {
        return authenticationSections.filter { it.groupId == areaId }
    }

    private fun buildFieldGroups(
        authenticationSectionId: UUID,
        selectorSectionValues: List<SelectorSectionValue>
    ): List<AuthenticationFieldGroupResponseData> {
        val fieldGroups = authenticationFieldGroupService.findAuthenticationFieldGroupBySectionId(authenticationSectionId)
        return fieldGroups.map { group ->
            AuthenticationFieldGroupResponseData(
                groupId = group.id,
                maxScore = group.maxScore,
                fields = buildFields(group.id, selectorSectionValues)
            )
        }
    }

    private fun buildFields(
        authenticationFieldGroupId: UUID,
        selectorSectionValues: List<SelectorSectionValue>
    ): List<AuthenticationSectionFieldResponseData> {
        val fields = authenticationFieldService.getAuthenticationFieldsByGroupId(authenticationFieldGroupId)
        return fields.map { field ->
            AuthenticationSectionFieldResponseData(
                fieldId = field.id,
                scoreDescription = field.description,
                example = field.placeHolder,
                fieldType = field.fieldInputType,
                values = buildSelectorValues(field.fieldInputType, selectorSectionValues, field.id)
            )
        }
    }

    private fun buildSelectorValues(
        type: FieldType,
        sectionValues: List<SelectorSectionValue>,
        authenticationFieldId: UUID
    ): List<AuthenticationSelectorValueResponseData>? {
        return if (type in listOf(FieldType.SELECT_VALUE, FieldType.SELECT, FieldType.BOOLEAN)) {
            sectionValues.filter { it.authenticationFieldId == authenticationFieldId }.map { value ->
                AuthenticationSelectorValueResponseData(
                    selectId = value.id,
                    value = value.name
                )
            }
        } else {
            null
        }
    }
}