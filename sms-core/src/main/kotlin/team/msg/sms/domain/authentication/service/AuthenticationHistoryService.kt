package team.msg.sms.domain.authentication.service

import team.msg.sms.common.annotation.Service

@Service
class AuthenticationHistoryService(
    getAuthenticationHistoryService: GetAuthenticationHistoryService,
    commandAuthenticationHistoryService: CommandAuthenticationHistoryService
) : GetAuthenticationHistoryService by getAuthenticationHistoryService,
    CommandAuthenticationHistoryService by commandAuthenticationHistoryService