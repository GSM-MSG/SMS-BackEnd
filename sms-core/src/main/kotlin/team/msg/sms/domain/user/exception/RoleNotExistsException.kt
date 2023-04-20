package team.msg.sms.domain.user.exception

import team.msg.sms.common.error.SmsException
import team.msg.sms.domain.user.exception.error.UserErrorCode

object RoleNotExistsException : SmsException(
    UserErrorCode.ROLE_NOT_EXISTS
)