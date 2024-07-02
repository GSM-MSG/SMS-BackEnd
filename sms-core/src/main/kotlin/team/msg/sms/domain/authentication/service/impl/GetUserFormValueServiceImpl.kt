package team.msg.sms.domain.authentication.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.authentication.model.UserFormValue
import team.msg.sms.domain.authentication.service.GetUserFormValueService
import team.msg.sms.domain.authentication.spi.UserFormValuePort
import java.util.*

@Service
class GetUserFormValueServiceImpl(
    private val userFormValuePort: UserFormValuePort
) : GetUserFormValueService {
    override fun getUserFormValueListByFieldIdAndStudentId(fieldId: UUID, studentId: UUID): List<UserFormValue> =
        userFormValuePort.queryUserFormValueListByFieldIdAndStudentId(fieldId, studentId)
}