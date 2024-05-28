package team.msg.sms.domain.authentication.exception

import team.msg.sms.common.error.SmsException
import team.msg.sms.domain.authentication.exception.error.AuthenticationErrorCode

object AuthenticationNotFoundException : SmsException(
    AuthenticationErrorCode.AUTHENTICATION_NOT_FOUND
)