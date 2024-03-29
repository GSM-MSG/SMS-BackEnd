package team.msg.sms.domain.authentication.service

import team.msg.sms.common.annotation.Service

@Service
class AuthenticationService(
    commandAuthenticationService: CommandAuthenticationService,
    getAuthenticationService: GetAuthenticationService,
    filterAuthenticationService: FilterAuthenticationService
) : CommandAuthenticationService by commandAuthenticationService,
    GetAuthenticationService by getAuthenticationService,
    FilterAuthenticationService by filterAuthenticationService