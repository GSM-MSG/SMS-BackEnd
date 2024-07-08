package team.msg.sms.domain.authentication.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.authentication.service.GetAuthenticationFormService
import team.msg.sms.domain.authentication.spi.AuthenticationFormPort
import java.util.*

@Service
class GetAuthenticationFormServiceImpl(
    private val authenticationFormPort: AuthenticationFormPort
) : GetAuthenticationFormService {
    override fun getActiveAuthenticationFormId(): UUID =
        authenticationFormPort.queryActiveAuthenticationFormId()
}