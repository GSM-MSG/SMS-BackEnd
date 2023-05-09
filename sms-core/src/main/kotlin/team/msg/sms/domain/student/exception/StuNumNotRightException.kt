package team.msg.sms.domain.student.exception

import team.msg.sms.common.error.SmsException
import team.msg.sms.domain.student.exception.error.StudentErrorCode

object StuNumNotRightException : SmsException(
    StudentErrorCode.STU_NUM_NOR_RIGHT
)