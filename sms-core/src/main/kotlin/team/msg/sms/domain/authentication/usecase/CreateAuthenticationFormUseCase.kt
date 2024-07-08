package team.msg.sms.domain.authentication.usecase

import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.authentication.dto.req.*
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
    private val authenticationFieldGroupService: AuthenticationFieldGroupService,
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
        requestData: CreateAuthenticationFormRequestData,
        teacher: Teacher
    ): AuthenticationForm =
        authenticationFormService.save(
            AuthenticationForm(
                id = UUID.randomUUID(),
                title = requestData.title,
                version = requestData.version,
                createdBy = teacher.id,
                createdAt = LocalDateTime.now(),
                active = false
            )
        )


    private fun saveFiles(files: List<CreateFileRequestData>, authenticationFormId: UUID) {
        val fileList = files.map {
            File(
                id = UUID.randomUUID(),
                fileName = it.name,
                fileUrl = it.url,
                fileType = FileType.AUTHENTICATION,
                targetId = authenticationFormId
            )
        }
        fileService.saveAll(fileList)
    }

    private fun createAuthenticationAreas(
        formData: List<CreateAuthenticationAreaFormRequestData>,
        authenticationFormId: UUID
    ) {
        formData.forEachIndexed { index, areaData ->
            val area = saveAuthenticationArea(areaData, authenticationFormId, index)
            createAuthenticationSections(areaData.data, area.id)
        }
    }

    private fun saveAuthenticationArea(
        areaData: CreateAuthenticationAreaFormRequestData,
        authenticationFormId: UUID,
        index: Int
    ): AuthenticationArea =
        authenticationAreaService.save(
            AuthenticationArea(
                id = UUID.randomUUID(),
                title = areaData.title,
                sort = index,
                authenticationFormId = authenticationFormId
            )
        )


    private fun createAuthenticationSections(
        sections: List<CreateAuthenticationSectionRequestData>,
        areaId: UUID
    ) {
        sections.forEachIndexed { index, sectionData ->
            val section = saveAuthenticationSection(sectionData, areaId, index)
            createAuthenticationFields(sectionData.fieldGroupData, section.id)
        }
    }

    private fun saveAuthenticationSection(
        sectionData: CreateAuthenticationSectionRequestData,
        areaId: UUID,
        index: Int
    ): AuthenticationSection =
        authenticationSectionService.save(
            AuthenticationSection(
                id = UUID.randomUUID(),
                groupId = areaId,
                sectionName = sectionData.sectionName,
                maxCount = sectionData.maxCount,
                sort = index
            )
        )

    private fun createAuthenticationFields(
        fieldGroupDataList: List<CreateAuthenticationFieldGroupRequestData>,
        sectionId: UUID
    ) {
        fieldGroupDataList.forEach { fieldGroupData ->
            val fieldGroup = saveFieldGroup(fieldGroupData.maxScore, sectionId)
            fieldGroupData.fieldData.forEachIndexed { index, fieldData ->
                val field = saveAuthenticationField(fieldData, fieldGroup.id, index)
                saveSelectorSectionValues(fieldData, field)
            }
        }
    }

    private fun saveFieldGroup(maxScore: Double, sectionId: UUID): AuthenticationFieldGroup =
        authenticationFieldGroupService.save(
            AuthenticationFieldGroup(
                id = UUID.randomUUID(),
                maxScore = maxScore,
                sectionId = sectionId
            )
        )


    private fun saveAuthenticationField(
        fieldData: CreateAuthenticationFieldRequestData,
        groupId: UUID,
        index: Int
    ): AuthenticationField =
        authenticationFieldService.save(
            AuthenticationField(
                id = UUID.randomUUID(),
                description = fieldData.description,
                placeholder = fieldData.placeholder,
                groupId = groupId,
                fieldInputType = fieldData.fieldType,
                sort = index
            )
        )


    private fun saveSelectorSectionValues(
        fieldData: CreateAuthenticationFieldRequestData,
        field: AuthenticationField
    ) {
        if (fieldData.fieldType in listOf(
                FieldType.SELECT,
                FieldType.SELECT_VALUE,
                FieldType.BOOLEAN
            )
        ) {
            val selectorValues = fieldData.selectorSectionName.mapIndexed { index, name ->
                SelectorSectionValue(
                    id = UUID.randomUUID(),
                    authenticationFieldId = field.id,
                    name = name,
                    sort = index
                )
            }
            selectorSectionValueService.saveAll(selectorValues)
        }
    }
}