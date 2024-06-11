package team.msg.sms.domain.authentication.usecase

import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.authentication.dto.req.SubmitUserFormRequestData
import team.msg.sms.domain.authentication.model.SectionType
import team.msg.sms.domain.authentication.model.UserFormValue
import team.msg.sms.domain.authentication.service.UserFormValueService
import team.msg.sms.domain.student.service.StudentService
import java.time.LocalDateTime
import java.util.*

@UseCase
class SubmitUserFormDataUseCase(
    private val userFormValueService: UserFormValueService,
    private val studentService: StudentService
) {
    fun execute(submitDataList: List<SubmitUserFormRequestData>, authenticationFormId: UUID) {
        val student = studentService.currentStudent()

        val userFormValues = submitDataList.flatMap { submitData ->
            submitData.objects.map { submitValue ->
                generateSelectorValueBySelectorType(
                    sectionId = submitData.sectionId,
                    sectionType = submitData.sectionType,
                    submitData = submitValue,
                    studentId = student.id,
                    authenticationFormId = authenticationFormId
                )
            }
        }

        userFormValueService.saveAll(userFormValues)
    }

    private fun generateSelectorValueBySelectorType(
        sectionId: UUID,
        sectionType: SectionType,
        submitData: SubmitUserFormRequestData.SubmitValueRequestData,
        studentId: UUID,
        authenticationFormId: UUID
    ): UserFormValue {
        val value = when (sectionType) {
            SectionType.SELECT_VALUE -> submitData.value
            SectionType.SELECT, SectionType.BOOLEAN -> null
            else -> submitData.value
        }

        val selectId = when (sectionType) {
            SectionType.SELECT_VALUE, SectionType.SELECT, SectionType.BOOLEAN -> submitData.selectId
            else -> null
        }

        return UserFormValue(
            id = UUID.randomUUID(),
            authenticationSectionId = sectionId,
            value = value,
            score = 0,
            sectionType = sectionType,
            targetId = selectId,
            createdAt = LocalDateTime.now(),
            createdBy = studentId,
            authenticationFormId = authenticationFormId
        )
    }
}
