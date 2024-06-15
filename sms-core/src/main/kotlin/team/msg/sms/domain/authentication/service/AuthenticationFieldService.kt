package team.msg.sms.domain.authentication.service

import team.msg.sms.common.annotation.Service

@Service
class AuthenticationFieldService(
    getAuthenticationFieldService: GetAuthenticationFieldService,
    commandAuthenticationFieldService: CommandAuthenticationFieldService
) : GetAuthenticationFieldService by getAuthenticationFieldService,
    CommandAuthenticationFieldService by commandAuthenticationFieldService