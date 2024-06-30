package team.msg.sms.domain.authentication.service

import team.msg.sms.common.annotation.Service

@Service
class AuthenticationFormService(
    commandAuthenticationFormService: CommandAuthenticationFormService,
    getAuthenticationFormService: GetAuthenticationFormService
) : CommandAuthenticationFormService by commandAuthenticationFormService,
    GetAuthenticationFormService by getAuthenticationFormService