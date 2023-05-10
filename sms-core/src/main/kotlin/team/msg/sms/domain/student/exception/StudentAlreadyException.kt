package team.msg.sms.domain.student.exception

import team.msg.sms.common.error.SmsException
import team.msg.sms.domain.student.exception.error.StudentErrorCode

object StudentAlreadyException: SmsException(
    StudentErrorCode.STUDENT_ALREADY
)