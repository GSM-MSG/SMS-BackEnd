package team.msg.sms.domain.authentication.service

import team.msg.sms.domain.authentication.model.AuthenticationForm

interface CommandAuthenticationFormService {
    fun save(authenticationForm: AuthenticationForm): AuthenticationForm
}