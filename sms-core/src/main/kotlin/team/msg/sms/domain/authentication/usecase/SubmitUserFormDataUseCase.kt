package team.msg.sms.domain.authentication.usecase

import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.authentication.dto.req.SubmitUserFormRequestData
import team.msg.sms.domain.authentication.model.FieldType
import team.msg.sms.domain.authentication.model.UserFormValue
import team.msg.sms.domain.authentication.service.AuthenticationSectionService
import team.msg.sms.domain.authentication.service.UserFormValueService
import team.msg.sms.domain.student.service.StudentService
import java.time.LocalDateTime
import java.util.*

@UseCase
class SubmitUserFormDataUseCase(
    private val userFormValueService: UserFormValueService,
    private val authenticationSectionService: AuthenticationSectionService,
    private val studentService: StudentService
) {
    fun execute(submitDataList: List<SubmitUserFormRequestData>, authenticationFormId: UUID) {
        val student = studentService.currentStudent()

        val userFormValues = submitDataList.flatMap { submitData ->
            val maxCount = authenticationSectionService.getMaxCountById(submitData.sectionId)
            val groupId = if (maxCount > 1) UUID.randomUUID() else null

            submitData.objects.map { submitValue ->
                createUserFormValue(
                    sectionId = submitData.sectionId,
                    submitData = submitValue,
                    groupId = groupId,
                    studentId = student.id,
                    authenticationFormId = authenticationFormId
                )
            }
        }

        userFormValueService.saveAll(userFormValues)
    }

    private fun createUserFormValue(
        sectionId: UUID,
        submitData: SubmitUserFormRequestData.SubmitValueRequestData,
        groupId: UUID?,
        studentId: UUID,
        authenticationFormId: UUID
    ): UserFormValue {
        val value = when (submitData.fieldType) {
            FieldType.SELECT_VALUE -> submitData.value
            FieldType.SELECT, FieldType.BOOLEAN -> null
            else -> submitData.value
        }

        val selectId = when (submitData.fieldType) {
            FieldType.SELECT_VALUE, FieldType.SELECT, FieldType.BOOLEAN -> submitData.selectId
            else -> null
        }

        return UserFormValue(
            id = UUID.randomUUID(),
            groupId = groupId,
            authenticationSectionId = sectionId,
            value = value,
            score = 0,
            fieldType = submitData.fieldType,
            targetId = selectId,
            createdAt = LocalDateTime.now(),
            createdBy = studentId,
            authenticationFormId = authenticationFormId,
            authenticationFieldId = submitData.fieldId
        )
    }
}
