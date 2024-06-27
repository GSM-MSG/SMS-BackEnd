package team.msg.sms.domain.authentication.service

import team.msg.sms.common.annotation.Service

@Service
class AuthenticationFieldGroupService(
    commandAuthenticationFieldGroupService: CommandAuthenticationFieldGroupService,
    getAuthenticationFieldGroupService: GetAuthenticationFieldGroupService
) : CommandAuthenticationFieldGroupService by commandAuthenticationFieldGroupService,
    GetAuthenticationFieldGroupService by getAuthenticationFieldGroupService