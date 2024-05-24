package team.msg.sms.domain.teacher.spi

import team.msg.sms.domain.teacher.model.Teacher
import team.msg.sms.domain.user.model.User

interface CommandTeacherPort {
    fun saveTeacher(teacher: Teacher, user: User): Teacher
}