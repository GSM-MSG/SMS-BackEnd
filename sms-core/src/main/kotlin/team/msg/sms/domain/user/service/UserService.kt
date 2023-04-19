package team.msg.sms.domain.user.service

import team.msg.sms.common.annotation.Service

@Service
class UserService(
    checkUserService: CheckUserService,
    getUserService: GetUserService,
    commandUserService: CommandUserService
) : GetUserService by getUserService,
    CheckUserService by checkUserService,
    CommandUserService by commandUserService