package team.msg.sms.domain.teacher.spi

import team.msg.sms.domain.teacher.model.Teacher
import team.msg.sms.domain.user.model.User
import java.util.*

interface QueryTeacherPort {
    fun existsTeacherById(uuid: UUID): Boolean
    fun existsTeacherByUser(user: User): Boolean
    fun findTeacherByUser(user: User): Teacher
    fun queryTeacherByUserId(userId: UUID): Teacher
}