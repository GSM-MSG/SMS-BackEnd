package team.msg.sms.domain.teacher.spi

import team.msg.sms.domain.teacher.model.HomeroomTeacher
import team.msg.sms.domain.teacher.model.Teacher

interface CommandHomeroomTeacherPort {
    fun saveHomeroomTeacher(homeroomTeacher: HomeroomTeacher, teacher: Teacher)
}