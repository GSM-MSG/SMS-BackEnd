package team.msg.sms.domain.user.exception

import team.msg.sms.common.error.SmsException
import team.msg.sms.domain.user.exception.error.UserErrorCode

object InternalServerErrorException : SmsException(
    UserErrorCode.INTERNAL_SERVER_ERROR
)