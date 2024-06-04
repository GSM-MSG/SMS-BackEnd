package team.msg.sms.domain.authentication.service

import team.msg.sms.common.annotation.Service

@Service
class AuthenticationSectionService(
    getAuthenticationSectionService: GetAuthenticationSectionService
) : GetAuthenticationSectionService by getAuthenticationSectionService