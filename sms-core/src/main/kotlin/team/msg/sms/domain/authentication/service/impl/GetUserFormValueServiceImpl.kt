package team.msg.sms.domain.authentication.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.authentication.service.GetUserFormValueService
import team.msg.sms.domain.authentication.spi.UserFormValuePort

@Service
class GetUserFormValueServiceImpl(
    private val userFormValuePort: UserFormValuePort
) : GetUserFormValueService {
}