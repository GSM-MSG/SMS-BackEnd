package team.msg.sms.domain.authentication.spi

import team.msg.sms.domain.authentication.model.AuthenticationField

interface CommandAuthenticationFieldPort {
    fun save(authenticationField: AuthenticationField): AuthenticationField
}