package team.msg.sms.domain.authentication.service

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.authentication.model.AuthenticationField

@Service
interface CommandAuthenticationFieldService {
    fun save(authenticationField: AuthenticationField): AuthenticationField
}