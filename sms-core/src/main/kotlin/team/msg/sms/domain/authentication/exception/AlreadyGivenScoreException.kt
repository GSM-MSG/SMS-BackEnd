package team.msg.sms.domain.authentication.exception

import team.msg.sms.common.error.SmsException
import team.msg.sms.domain.authentication.exception.error.AuthenticationErrorCode

object AlreadyGivenScoreException : SmsException(
    AuthenticationErrorCode.ALREADY_GIVEN_SCORE
)