package team.msg.sms.domain.authentication.usecase

import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.authentication.dto.req.CreateAuthenticationFormRequestData
import team.msg.sms.domain.authentication.model.*
import team.msg.sms.domain.authentication.service.AuthenticationFormService
import team.msg.sms.domain.authentication.service.AuthenticationSectionService
import team.msg.sms.domain.authentication.service.GroupAuthenticationAreaService
import team.msg.sms.domain.authentication.service.SelectorSectionValueService
import team.msg.sms.domain.teacher.service.TeacherService
import java.time.LocalDateTime
import java.util.*

@UseCase
class CreateAuthenticationFormUseCase(
    private val groupAuthenticationAreaService: GroupAuthenticationAreaService,
    private val authenticationFormService: AuthenticationFormService,
    private val authenticationSectionService: AuthenticationSectionService,
    private val selectorSectionValueService: SelectorSectionValueService,
    private val teacherService: TeacherService
) {
    fun execute(createAuthenticationFromRequestData: CreateAuthenticationFormRequestData) {
        val teacher = teacherService.currentTeacher()

        val authenticationForm = authenticationFormService.save(
            AuthenticationForm(
                id = UUID.randomUUID(),
                title = createAuthenticationFromRequestData.title,
                version = createAuthenticationFromRequestData.version,
                createdBy = teacher.id,
                createdAt = LocalDateTime.now()
            )
        )

        createAuthenticationFromRequestData.formData.forEachIndexed { groupIndex, groupData ->
            groupAuthenticationAreaService.save(
                GroupAuthenticationArea(
                    id = UUID.randomUUID(),
                    title = groupData.title,
                    sort = groupIndex,
                    authenticationFormId = authenticationForm.id
                )
            ).also { group ->
                groupData.data.forEach { sectionData ->
                    authenticationSectionService.save(
                        AuthenticationSection(
                            id = UUID.randomUUID(),
                            groupId = group.id,
                            sectionName = sectionData.sectionName,
                            description = sectionData.description,
                            placeHolder = sectionData.placeHolder,
                            sectionType = sectionData.sectionType,
                            maxCount = sectionData.maxCount,
                            sectionScore = sectionData.sectionScore
                        )
                    ).also { section ->
                        if (sectionData.sectionType in listOf(
                                SectionType.SELECT,
                                SectionType.SELECT_VALUE,
                                SectionType.BOOLEAN
                            )
                        ) {
                            sectionData.selectorSectionName.mapIndexed { selectorIndex, name ->
                                SelectorSectionValue(
                                    id = UUID.randomUUID(),
                                    authenticationSectionId = section.id,
                                    name = name,
                                    sort = selectorIndex
                                )
                            }.also { selectorSectionValues ->
                                selectorSectionValueService.saveAll(selectorSectionValues)
                            }
                        }
                    }
                }
            }
        }
    }
}
