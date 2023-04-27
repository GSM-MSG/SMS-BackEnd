package team.msg.sms.domain.auth.service

import team.msg.sms.common.annotation.Service

@Service
class AuthService(
    commandAuthService: CommandAuthService,
    getAuthService: GetAuthService
) : CommandAuthService by commandAuthService,
    GetAuthService by getAuthService