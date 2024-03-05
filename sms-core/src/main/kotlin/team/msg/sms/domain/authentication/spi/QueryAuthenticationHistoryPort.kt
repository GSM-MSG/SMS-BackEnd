package team.msg.sms.domain.authentication.spi

import team.msg.sms.domain.authentication.model.Authentication
import team.msg.sms.domain.authentication.model.AuthenticationHistory

interface QueryAuthenticationHistoryPort {
    fun queryLatestAuthenticationHistory(authentication: Authentication): AuthenticationHistory
}