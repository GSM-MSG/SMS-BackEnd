package team.msg.sms.domain.authentication.spi

import team.msg.sms.domain.authentication.model.AuthenticationFieldGroup

interface CommandAuthenticationFieldGroupPort {
    fun save(authenticationFieldGroup: AuthenticationFieldGroup): AuthenticationFieldGroup
}