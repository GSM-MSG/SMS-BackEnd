package team.msg.sms.domain.authentication.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.authentication.model.AuthenticationField
import team.msg.sms.domain.authentication.service.CommandAuthenticationFieldService
import team.msg.sms.domain.authentication.spi.CommandAuthenticationFieldPort

@Service
class CommandAuthenticationFieldServiceImpl(
    private val commandAuthenticationFieldPort: CommandAuthenticationFieldPort
): CommandAuthenticationFieldService {
    override fun save(authenticationField: AuthenticationField): AuthenticationField =
        commandAuthenticationFieldPort.save(authenticationField)
}