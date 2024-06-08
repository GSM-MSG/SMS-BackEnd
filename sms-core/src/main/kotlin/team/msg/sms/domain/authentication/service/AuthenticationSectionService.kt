package team.msg.sms.domain.authentication.service

import team.msg.sms.common.annotation.Service

@Service
class AuthenticationSectionService(
    getAuthenticationSectionService: GetAuthenticationSectionService,
    commandAuthenticationSectionService: CommandAuthenticationSectionService
) : GetAuthenticationSectionService by getAuthenticationSectionService,
    CommandAuthenticationSectionService by commandAuthenticationSectionService