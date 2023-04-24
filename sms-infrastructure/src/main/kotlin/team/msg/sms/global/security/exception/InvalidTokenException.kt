package team.msg.sms.global.security.exception

import team.msg.sms.common.error.SmsException
import team.msg.sms.global.security.exception.error.SecurityErrorCode

object InvalidTokenException : SmsException(
    SecurityErrorCode.INVALID_TOKEN
)