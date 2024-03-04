package team.msg.sms.domain.authentication.service

import team.msg.sms.common.annotation.Service

@Service
class AuthenticationService(
    commandAuthenticationService: CommandAuthenticationService
) : CommandAuthenticationService by commandAuthenticationService