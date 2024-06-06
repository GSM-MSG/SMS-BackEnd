package team.msg.sms.domain.authentication.service

import team.msg.sms.common.annotation.Service

@Service
class GroupAuthenticationAreaService(
    getGroupAuthenticationAreaService: GetGroupAuthenticationAreaService
) : GetGroupAuthenticationAreaService by getGroupAuthenticationAreaService