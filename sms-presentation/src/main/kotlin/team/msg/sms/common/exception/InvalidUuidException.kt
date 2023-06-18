package team.msg.sms.common.exception

import team.msg.sms.common.error.SmsException
import team.msg.sms.common.error.ValidErrorCode

object InvalidUuidException : SmsException(
    ValidErrorCode.BAD_REQUEST
)