package team.msg.sms.domain.user.exception

import team.msg.sms.common.error.SmsException
import team.msg.sms.domain.user.exception.error.UserErrorCode

object UserNotFoundException : SmsException(
    UserErrorCode.USER_NOT_FOUND
)