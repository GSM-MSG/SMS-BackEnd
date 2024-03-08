package team.msg.sms.domain.teacher.exception

import team.msg.sms.common.error.SmsException
import team.msg.sms.domain.teacher.exception.error.TeacherErrorCode

object HomeroomTeacherNotFoundException : SmsException(
    TeacherErrorCode.HOMEROOM_TEACHER_NOT_FOUNT
)