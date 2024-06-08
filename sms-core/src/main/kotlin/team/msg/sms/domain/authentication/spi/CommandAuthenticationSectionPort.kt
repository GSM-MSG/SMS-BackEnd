package team.msg.sms.domain.authentication.spi

import team.msg.sms.domain.authentication.model.AuthenticationSection

interface CommandAuthenticationSectionPort {
    fun save(authenticationSection: AuthenticationSection): AuthenticationSection
}