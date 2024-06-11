package team.msg.sms.domain.authentication.usecase

import org.springframework.transaction.annotation.Transactional
import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.authentication.dto.res.*
import team.msg.sms.domain.authentication.model.SectionType
import team.msg.sms.domain.authentication.model.SelectorSectionValue
import team.msg.sms.domain.authentication.service.AuthenticationSectionService
import team.msg.sms.domain.authentication.service.GroupAuthenticationAreaService
import team.msg.sms.domain.authentication.service.SelectorSectionValueService
import team.msg.sms.domain.file.dto.res.FileResponseData
import team.msg.sms.domain.file.service.FileService
import java.util.*

@UseCase
class QueryAuthenticationFormUseCase(
    private val authenticationSectionService: AuthenticationSectionService,
    private val fileService: FileService,
    private val selectorSectionValueService: SelectorSectionValueService,
    private val groupAuthenticationAreaService: GroupAuthenticationAreaService
) {
    @Transactional(readOnly = true)
    fun execute(authenticationFormId: UUID): QueryAuthenticationFormResponseData {
        val groups = groupAuthenticationAreaService.getGroupAuthenticationAreaByAuthenticationFormId(authenticationFormId)
        val groupIds = groups.map { it.id }
        val files = fileService.getFileByTargetUuidsAndTypeEqualsAuthentication(targetIds = groupIds)
        val authenticationSections =
            authenticationSectionService.getAuthenticationSectionByGroupIds(groupIds = groupIds)
        val selectorSectionValues = selectorSectionValueService.getSelectorSectionValue()

        return QueryAuthenticationFormResponseData(
            files = files.map { file ->
                FileResponseData(
                    name = file.fileName,
                    url = file.fileUrl
                )
            },
            content = groups.map { group ->
                AuthenticationAreaFormResponseData(
                    title = group.title,
                    sections = authenticationSections
                        .filter { it.groupId == group.id }
                        .map { authenticationSection ->
                            AuthenticationSectionResponseData(
                                sectionId = authenticationSection.id,
                                section = authenticationSection.sectionName,
                                scoreDescription = authenticationSection.description,
                                sectionScore = authenticationSection.sectionScore,
                                maxCount = authenticationSection.maxCount,
                                fields = listOf(
                                    AuthenticationSectionFieldResponseData(
                                        sectionType = authenticationSection.sectionType,
                                        values = generateSelectorValues(
                                            type = authenticationSection.sectionType,
                                            sectionValue = selectorSectionValues,
                                            authenticationSectionId = authenticationSection.id
                                        ),
                                        example = authenticationSection.placeHolder
                                    )
                                )
                            )
                        }
                )
            }
        )
    }

    private fun generateSelectorValues(
        type: SectionType,
        sectionValue: List<SelectorSectionValue>,
        authenticationSectionId: UUID
    ): List<AuthenticationSelectorValueResponseData>? {
        return if (type == SectionType.SELECT_VALUE || type == SectionType.SELECT || type == SectionType.BOOLEAN) {
            sectionValue.filter { it.authenticationSectionId == authenticationSectionId }
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