package team.msg.sms.domain.techstack.exception

import team.msg.sms.common.error.SmsException
import team.msg.sms.domain.techstack.exception.error.TechStackErrorCode

object TechStackNotFoundException : SmsException(
    TechStackErrorCode.TECH_STACK_NOT_FOUND
)