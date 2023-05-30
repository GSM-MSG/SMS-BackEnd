package team.msg.sms.domain.auth.exception

import team.msg.sms.common.error.SmsException
import team.msg.sms.domain.auth.exception.error.AuthErrorCode

object SecretMismatchException : SmsException(
    AuthErrorCode.SECRET_MISMATCH
)