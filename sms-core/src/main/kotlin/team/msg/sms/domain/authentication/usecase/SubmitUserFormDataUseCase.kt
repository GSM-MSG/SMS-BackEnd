package team.msg.sms.domain.authentication.usecase

import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.authentication.dto.req.SubmitUserFormRequestData
import team.msg.sms.domain.authentication.model.FieldType
import team.msg.sms.domain.authentication.model.MarkingBoard
import team.msg.sms.domain.authentication.model.MarkingBoardType
import team.msg.sms.domain.authentication.model.UserFormValue
import team.msg.sms.domain.authentication.service.AuthenticationSectionService
import team.msg.sms.domain.authentication.service.MarkingBoardService
import team.msg.sms.domain.authentication.service.UserFormValueService
import team.msg.sms.domain.student.service.StudentService
import java.time.LocalDateTime
import java.util.*

@UseCase
class SubmitUserFormDataUseCase(
    private val userFormValueService: UserFormValueService,
    private val markingBoardService: MarkingBoardService,
    private val studentService: StudentService
) {
    fun execute(submitDataList: List<SubmitUserFormRequestData>, authenticationFormId: UUID) {
        val student = studentService.currentStudent()
        val userFormValues = submitDataList.flatMap { submitData ->

            submitData.objects.flatMap { submitValue ->
                val groupId = UUID.randomUUID()

                submitValue.fields.map { submitFieldValue ->
                    createUserFormValue(
                        sectionId = submitData.sectionId,
                        submitData = submitFieldValue,
                        authenticationFieldGroupId = submitValue.groupId,
                        groupId = groupId,
                        studentId = student.id,
                        authenticationFormId = authenticationFormId
                    )
                }
            }
        }
        userFormValueService.saveAll(userFormValues)

        val markingBoard = MarkingBoard(
            id = UUID.randomUUID(),
            title = "${student.name} ${student.stuNum}",
            markingBoardType = MarkingBoardType.PENDING_REVIEW,
            authenticationId = authenticationFormId,
            studentId = student.id,
        )
        markingBoardService.save(markingBoard)
    }

    private fun createUserFormValue(
        sectionId: UUID,
        submitData: SubmitUserFormRequestData.SubmitFieldValueRequestData,
        groupId: UUID,
        authenticationFieldGroupId: UUID,
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
            setId = groupId,
            authenticationSectionId = sectionId,
            authenticationFieldGroupId = authenticationFieldGroupId,
            value = value,
            fieldType = submitData.fieldType,
            targetId = selectId,
            createdAt = LocalDateTime.now(),
            createdBy = studentId,
            authenticationFormId = authenticationFormId,
            authenticationFieldId = submitData.fieldId
        )
    }
}