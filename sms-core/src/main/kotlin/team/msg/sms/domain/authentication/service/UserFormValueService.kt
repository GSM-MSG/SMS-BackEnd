package team.msg.sms.domain.authentication.service

import team.msg.sms.common.annotation.Service

@Service
class UserFormValueService(
    getUserFormValueService: GetUserFormValueService,
    commandUserFormValueService: CommandUserFormValueService,
    checkUserFormValueService: CheckUserFormValueService
) : GetUserFormValueService by getUserFormValueService,
    CommandUserFormValueService by commandUserFormValueService,
    CheckUserFormValueService by checkUserFormValueService