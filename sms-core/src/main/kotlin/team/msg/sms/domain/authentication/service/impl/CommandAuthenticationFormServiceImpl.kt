package team.msg.sms.domain.authentication.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.authentication.model.AuthenticationForm
import team.msg.sms.domain.authentication.service.CommandAuthenticationFormService
import team.msg.sms.domain.authentication.spi.AuthenticationFormPort

@Service
class CommandAuthenticationFormServiceImpl(
    private val authenticationFormPort: AuthenticationFormPort
) : CommandAuthenticationFormService {
    override fun save(authenticationForm: AuthenticationForm): AuthenticationForm =
        authenticationFormPort.save(authenticationForm)
}