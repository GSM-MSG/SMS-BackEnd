package team.msg.sms.domain.authentication.spi

import team.msg.sms.domain.authentication.model.AuthenticationForm

interface CommandAuthenticationFormPort {
    fun save(authenticationForm: AuthenticationForm): AuthenticationForm
}