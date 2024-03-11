package team.msg.sms.domain.authentication.exception

import team.msg.sms.common.error.SmsException
import team.msg.sms.domain.authentication.exception.error.AuthenticationErrorCode

object InvalidGradeClassException : SmsException(
    AuthenticationErrorCode.INVALID_GRADE_CLASS
)