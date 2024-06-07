package team.msg.sms.domain.authentication.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.authentication.model.UserFormValue
import team.msg.sms.domain.authentication.service.CommandUserFormValueService
import team.msg.sms.domain.authentication.spi.UserFormValuePort

@Service
class CommandUserFormValueServiceImpl(
    private val userFormValuePort: UserFormValuePort
) : CommandUserFormValueService {
    override fun saveAll(userFormValueList: List<UserFormValue>): List<UserFormValue> {
        return userFormValuePort.saveAll(userFormValueList)
    }
}