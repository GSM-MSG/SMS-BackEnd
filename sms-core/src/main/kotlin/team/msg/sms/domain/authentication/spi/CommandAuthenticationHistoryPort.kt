package team.msg.sms.domain.authentication.spi

import team.msg.sms.domain.authentication.model.AuthenticationHistory

interface CommandAuthenticationHistoryPort {
    fun save(authenticationHistory: AuthenticationHistory): AuthenticationHistory
}