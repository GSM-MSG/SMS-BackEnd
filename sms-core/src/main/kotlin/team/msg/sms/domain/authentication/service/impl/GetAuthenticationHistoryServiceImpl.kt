package team.msg.sms.domain.authentication.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.authentication.model.Authentication
import team.msg.sms.domain.authentication.model.AuthenticationHistory
import team.msg.sms.domain.authentication.service.GetAuthenticationHistoryService
import team.msg.sms.domain.authentication.spi.QueryAuthenticationHistoryPort

@Service
class GetAuthenticationHistoryServiceImpl(
    private val queryAuthenticationHistoryPort: QueryAuthenticationHistoryPort
) : GetAuthenticationHistoryService{
    override fun getLatestAuthenticationHistory(authentication: Authentication): AuthenticationHistory =
        queryAuthenticationHistoryPort.queryLatestAuthenticationHistory(authentication)

}