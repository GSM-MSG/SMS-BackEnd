package team.msg.sms.domain.authentication.service

import team.msg.sms.domain.authentication.model.Authentication
import team.msg.sms.domain.authentication.model.AuthenticationHistory
import java.util.UUID

interface GetAuthenticationHistoryService {
    fun getLatestAuthenticationHistory(authentication: Authentication): AuthenticationHistory
}