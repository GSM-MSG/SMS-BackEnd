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
        userFormValueService.saveAll(
            userFormValueList = submitDataList.map { submitData ->
                generateSelectorValueBySelectorType(
                    submitData = submitData,
                    studentId = student.id,
                    authenticationFormId = authenticationFormId
                )
            }
        )
    }
}

private fun generateSelectorValueBySelectorType(
    submitData: SubmitUserFormRequestData,
    studentId: UUID,
    authenticationFormId: UUID
): UserFormValue {
    val value = when (submitData.sectionType) {
        SectionType.SELECT_VALUE -> submitData.value
        SectionType.SELECT, SectionType.BOOLEAN -> null
        else -> submitData.value
    }

    val targetId = when (submitData.sectionType) {
        SectionType.SELECT_VALUE, SectionType.SELECT, SectionType.BOOLEAN -> submitData.targetId
        else -> null
    }

    return UserFormValue(
        id = UUID.randomUUID(),
        authenticationSectionId = submitData.authenticationSectionId,
        value = value,
        score = 0,
        sectionType = submitData.sectionType,
        targetId = targetId,
        createdAt = LocalDateTime.now(),
        createdBy = studentId,
        authenticationFormId = authenticationFormId
    )
}
}
