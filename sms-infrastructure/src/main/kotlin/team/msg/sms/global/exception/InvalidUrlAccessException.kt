package team.msg.sms.global.exception

import team.msg.sms.common.error.SmsException
import team.msg.sms.global.error.GlobalErrorCode

object InvalidUrlAccessException : SmsException(
    GlobalErrorCode.FOUND
)