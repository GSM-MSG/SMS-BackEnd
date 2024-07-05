package team.msg.sms.domain.authentication.exception

import team.msg.sms.common.error.SmsException
import team.msg.sms.domain.authentication.exception.error.AuthenticationErrorCode

object UserFormValueNotFoundException : SmsException(
    AuthenticationErrorCode.USER_FORM_VALUE_NOT_FOUND
)