package team.msg.sms.global.error

import team.msg.sms.common.error.SmsException

object InternalServerError : SmsException(
    GlobalErrorCode.INTERNAL_SERVER_ERROR
)