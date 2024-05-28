package team.msg.sms.domain.authentication.exception

import team.msg.sms.common.error.SmsException
import team.msg.sms.domain.authentication.exception.error.AuthenticationErrorCode

object UnsuitableActivityStatusException : SmsException(
    AuthenticationErrorCode.UNSUITABLE_ACTIVITY_STATUS
)