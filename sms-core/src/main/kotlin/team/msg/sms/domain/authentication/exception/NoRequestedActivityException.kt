package team.msg.sms.domain.authentication.exception

import team.msg.sms.common.error.SmsException
import team.msg.sms.domain.authentication.exception.error.AuthenticationErrorCode

object NoRequestedActivityException : SmsException(
    AuthenticationErrorCode.NO_REQUESTED_ACTIVITY
)