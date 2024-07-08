package team.msg.sms.domain.authentication.service

import team.msg.sms.domain.authentication.model.UserFormValue
import java.util.UUID

interface GetUserFormValueService {
    fun getUserFormValueListByFieldIdAndStudentId(
        fieldId: UUID,
        studentId: UUID
    ): List<UserFormValue>
}