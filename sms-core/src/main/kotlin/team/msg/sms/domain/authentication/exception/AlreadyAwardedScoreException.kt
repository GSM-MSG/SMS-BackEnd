package team.msg.sms.domain.authentication.exception

import team.msg.sms.common.error.SmsException
import team.msg.sms.domain.authentication.exception.error.AuthenticationErrorCode

object AlreadyAwardedScoreException : SmsException(
    AuthenticationErrorCode.ALREADY_AWARDED_SCORE
)