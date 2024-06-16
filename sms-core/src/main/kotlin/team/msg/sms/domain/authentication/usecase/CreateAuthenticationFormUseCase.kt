package team.msg.sms.domain.authentication.usecase

import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.authentication.dto.req.CreateAuthenticationAreaFormRequestData
import team.msg.sms.domain.authentication.dto.req.CreateAuthenticationFieldRequestData
import team.msg.sms.domain.authentication.dto.req.CreateAuthenticationFormRequestData
import team.msg.sms.domain.authentication.dto.req.CreateAuthenticationSectionRequestData
import team.msg.sms.domain.authentication.model.*
import team.msg.sms.domain.authentication.service.*
import team.msg.sms.domain.file.dto.req.CreateFileRequestData
import team.msg.sms.domain.file.model.File
import team.msg.sms.domain.file.model.FileType
import team.msg.sms.domain.file.service.FileService
import team.msg.sms.domain.teacher.model.Teacher
import team.msg.sms.domain.teacher.service.TeacherService
import java.time.LocalDateTime
import java.util.*

@UseCase
class CreateAuthenticationFormUseCase(
    private val authenticationAreaService: AuthenticationAreaService,
    private val authenticationFormService: AuthenticationFormService,
    private val authenticationSectionService: AuthenticationSectionService,
    private val authenticationFieldService: AuthenticationFieldService,
    private val selectorSectionValueService: SelectorSectionValueService,
    private val fileService: FileService,
    private val teacherService: TeacherService
) {
    fun execute(createAuthenticationFormRequestData: CreateAuthenticationFormRequestData) {
        val teacher = teacherService.currentTeacher()
        val authenticationForm = saveAuthenticationForm(createAuthenticationFormRequestData, teacher)

        saveFiles(createAuthenticationFormRequestData.files, authenticationForm.id)
        createAuthenticationAreas(createAuthenticationFormRequestData.formData, authenticationForm.id)
    }

    private fun saveAuthenticationForm(
        createAuthenticationFormRequestData: CreateAuthenticationFormRequestData,
        teacher: Teacher
    ): AuthenticationForm {
        return authenticationFormService.save(
            AuthenticationForm(
                id = UUID.randomUUID(),
                title = createAuthenticationFormRequestData.title,
                version = createAuthenticationFormRequestData.version,
                createdBy = teacher.id,
                createdAt = LocalDateTime.now()
            )
        )
    }

    private fun saveFiles(files: List<CreateFileRequestData>, authenticationFormId: UUID) {
        fileService.saveAll(
            files.map {
                File(
                    id = UUID.randomUUID(),
                    fileName = it.name,
                    fileUrl = it.url,
                    fileType = FileType.AUTHENTICATION,
                    targetId = authenticationFormId
                )
            }
        )
    }

    private fun createAuthenticationAreas(
        formData: List<CreateAuthenticationAreaFormRequestData>,
        authenticationFormId: UUID
    ) {
        formData.forEachIndexed { groupIndex, groupData ->
            val group = saveAuthenticationArea(groupData, authenticationFormId, groupIndex)
            createAuthenticationSections(groupData.data, group.id)
        }
    }

    private fun saveAuthenticationArea(
        groupData: CreateAuthenticationAreaFormRequestData,
        authenticationFormId: UUID,
        groupIndex: Int
    ): AuthenticationArea {
        return authenticationAreaService.save(
            AuthenticationArea(
                id = UUID.randomUUID(),
                title = groupData.title,
                sort = groupIndex,
                authenticationFormId = authenticationFormId
            )
        )
    }

    private fun createAuthenticationSections(
        sectionDataList: List<CreateAuthenticationSectionRequestData>,
        groupId: UUID
    ) {
        sectionDataList.forEachIndexed { index, sectionData ->
            val section = saveAuthenticationSection(sectionData, groupId, index)
            createAuthenticationFields(sectionData.fieldData, section.id)
        }
    }

    private fun saveAuthenticationSection(
        sectionData: CreateAuthenticationSectionRequestData,
        groupId: UUID,
        index: Int
    ): AuthenticationSection {
        return authenticationSectionService.save(
            AuthenticationSection(
                id = UUID.randomUUID(),
                groupId = groupId,
                sectionName = sectionData.sectionName,
                maxCount = sectionData.maxCount,
                sort = index
            )
        )
    }

    private fun createAuthenticationFields(
        fieldDataList: List<CreateAuthenticationFieldRequestData>,
        sectionId: UUID
    ) {
        fieldDataList.forEachIndexed { sortIndex, fieldData ->
            val field = saveAuthenticationField(fieldData, sectionId, sortIndex)
            saveSelectorSectionValues(fieldData, field)
        }
    }

    private fun saveAuthenticationField(
        fieldData: CreateAuthenticationFieldRequestData,
        sectionId: UUID,
        index: Int
    ): AuthenticationField {
        return authenticationFieldService.save(
            AuthenticationField(
                id = UUID.randomUUID(),
                sectionId = sectionId,
                description = fieldData.description,
                placeHolder = fieldData.placeHolder,
                fieldInputType = fieldData.fieldInputType,
                fieldScore = 0.0,
                sort = index
            )
        )
    }

    private fun saveSelectorSectionValues(
        fieldData: CreateAuthenticationFieldRequestData,
        field: AuthenticationField
    ) {
        if (fieldData.fieldInputType in listOf(
                FieldType.SELECT,
                FieldType.SELECT_VALUE,
                FieldType.BOOLEAN
            )
        ) {
            val selectorSectionValues = fieldData.selectorSectionName.mapIndexed { selectorIndex, name ->
                SelectorSectionValue(
                    id = UUID.randomUUID(),
                    authenticationFieldId = field.id,
                    name = name,
                    sort = selectorIndex
                )
            }
            selectorSectionValueService.saveAll(selectorSectionValues)
        }
    }
}
