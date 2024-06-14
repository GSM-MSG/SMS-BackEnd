package team.msg.sms.domain.authentication.usecase

import org.springframework.transaction.annotation.Transactional
import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.authentication.dto.res.*
import team.msg.sms.domain.authentication.model.AuthenticationSection
import team.msg.sms.domain.authentication.model.AuthenticationArea
import team.msg.sms.domain.authentication.model.SectionType
import team.msg.sms.domain.authentication.model.SelectorSectionValue
import team.msg.sms.domain.authentication.service.AuthenticationFieldService
import team.msg.sms.domain.authentication.service.AuthenticationSectionService
import team.msg.sms.domain.authentication.service.AuthenticationAreaService
import team.msg.sms.domain.authentication.service.SelectorSectionValueService
import team.msg.sms.domain.file.dto.res.FileResponseData
import team.msg.sms.domain.file.service.FileService
import java.util.*

@UseCase
class QueryAuthenticationFormUseCase(
    private val authenticationSectionService: AuthenticationSectionService,
    private val fileService: FileService,
    private val selectorSectionValueService: SelectorSectionValueService,
    private val authenticationFieldService: AuthenticationFieldService,
    private val authenticationAreaService: AuthenticationAreaService
) {
    @Transactional(readOnly = true)
    fun execute(authenticationFormId: UUID): QueryAuthenticationFormResponseData {
        val files = getFilesForGroups(authenticationFormId)
        val groups = getAuthenticationGroups(authenticationFormId)
        val authenticationSections = getAuthenticationSectionsForGroups(groups)
        val selectorSectionValues = selectorSectionValueService.getSelectorSectionValue()

        return buildQueryResponse(groups, files, authenticationSections, selectorSectionValues)
    }

    private fun getAuthenticationGroups(authenticationFormId: UUID): List<AuthenticationArea> {
        return authenticationAreaService.getGroupAuthenticationAreaByAuthenticationFormId(authenticationFormId)
    }

    private fun getFilesForGroups(authenticationFormId: UUID): List<FileResponseData> {
        return fileService.getFileByTargetUuidAndTypeEqualsAuthentication(targetId = authenticationFormId)
            .map { file ->
                FileResponseData(
                    name = file.fileName,
                    url = file.fileUrl
                )
            }
    }

    private fun getAuthenticationSectionsForGroups(groups: List<AuthenticationArea>): List<AuthenticationSection> {
        val groupIds = groups.map { it.id }
        return authenticationSectionService.getAuthenticationSectionByGroupIds(groupIds = groupIds)
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
                    sections = authenticationSections
                        .filter { it.groupId == group.id }
                        .map { authenticationSection ->
                            AuthenticationSectionResponseData(
                                sectionId = authenticationSection.id,
                                sectionName = authenticationSection.sectionName,
                                maxCount = authenticationSection.maxCount,
                                fields = getAuthenticationFields(authenticationSection, selectorSectionValues)
                            )
                        }
                )
            }
        )
    }

    private fun getAuthenticationFields(
        authenticationSection: AuthenticationSection,
        selectorSectionValues: List<SelectorSectionValue>
    ): List<AuthenticationSectionFieldResponseData> {
        return authenticationFieldService.getAuthenticationFieldsBySectionId(authenticationSection.id)
            .map { authenticationField ->
                AuthenticationSectionFieldResponseData(
                    fieldId = authenticationField.id,
                    scoreDescription = authenticationField.description,
                    example = authenticationField.placeHolder,
                    sectionType = authenticationField.fieldInputType,
                    values = generateSelectorValues(
                        authenticationField.fieldInputType,
                        selectorSectionValues,
                        authenticationField.id
                    )
                )
            }
    }

    private fun generateSelectorValues(
        type: SectionType,
        sectionValue: List<SelectorSectionValue>,
        authenticationFieldId: UUID
    ): List<AuthenticationSelectorValueResponseData>? {
        return if (type in listOf(SectionType.SELECT_VALUE, SectionType.SELECT, SectionType.BOOLEAN)) {
            sectionValue.filter { it.authenticationFieldId == authenticationFieldId }
                .map {
                    AuthenticationSelectorValueResponseData(
                        selectId = it.id,
                        value = it.name
                    )
                }
        } else {
            null
        }
    }
}
