package team.msg.sms.domain.authentication.service

import team.msg.sms.domain.authentication.model.AuthenticationSection

interface CommandAuthenticationSectionService {
    fun save(authenticationSection: AuthenticationSection): AuthenticationSection
}