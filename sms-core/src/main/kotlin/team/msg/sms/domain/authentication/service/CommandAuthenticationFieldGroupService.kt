package team.msg.sms.domain.authentication.service

import team.msg.sms.domain.authentication.model.AuthenticationFieldGroup

interface CommandAuthenticationFieldGroupService {
    fun save(authenticationFieldGroup: AuthenticationFieldGroup): AuthenticationFieldGroup
}