package team.msg.sms.domain.authentication.service

import team.msg.sms.common.annotation.Service

@Service
class AuthenticationAreaService(
    getGroupAuthenticationAreaService: GetAuthenticationAreaService,
    commandGroupAuthenticationAreaService: CommandAuthenticationAreaService
) : GetAuthenticationAreaService by getGroupAuthenticationAreaService,
    CommandAuthenticationAreaService by commandGroupAuthenticationAreaService