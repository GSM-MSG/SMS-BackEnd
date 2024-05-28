package team.msg.sms.domain.student.exception

import team.msg.sms.common.error.SmsException
import team.msg.sms.domain.student.exception.error.StudentErrorCode

object StudentLinkNotFoundException : SmsException(
    StudentErrorCode.STUDENT_LINK_NOT_FOUND
)