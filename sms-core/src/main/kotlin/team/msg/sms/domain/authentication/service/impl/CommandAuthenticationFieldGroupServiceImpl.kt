package team.msg.sms.domain.authentication.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.authentication.model.AuthenticationFieldGroup
import team.msg.sms.domain.authentication.service.CommandAuthenticationFieldGroupService
import team.msg.sms.domain.authentication.spi.AuthenticationFieldGroupPort

@Service
class CommandAuthenticationFieldGroupServiceImpl(
    private val authenticationFieldGroupPort: AuthenticationFieldGroupPort
) : CommandAuthenticationFieldGroupService {
    override fun save(authenticationFieldGroup: AuthenticationFieldGroup): AuthenticationFieldGroup =
        authenticationFieldGroupPort.save(authenticationFieldGroup)
}