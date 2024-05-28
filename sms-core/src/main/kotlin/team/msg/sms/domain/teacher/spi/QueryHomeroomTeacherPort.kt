package team.msg.sms.domain.teacher.spi

import team.msg.sms.domain.teacher.model.HomeroomTeacher
import team.msg.sms.domain.teacher.model.Teacher
import team.msg.sms.domain.user.model.User
import java.util.UUID

interface QueryHomeroomTeacherPort {
    fun existsHomeroomTeacherByGradeAndClassNum(grade: Int, classNum: Int): Boolean
    fun queryHomeroomTeacherByUserId(userId: UUID): HomeroomTeacher?
    fun findHomeroomTeacherByTeacher(teacher: Teacher, user: User): HomeroomTeacher
}