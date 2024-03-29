package team.msg.sms.domain.teacher.service

import team.msg.sms.domain.teacher.model.HomeroomTeacher
import team.msg.sms.domain.teacher.model.Teacher
import team.msg.sms.domain.user.model.User
import java.util.UUID

interface GetHomeroomTeacherService {
    fun getHomeroomTeacherByUserId (userId: UUID): HomeroomTeacher
    fun getHomeroomTeacherByTeacher(teacher: Teacher, user: User): HomeroomTeacher
}