package team.msg.sms.domain.teacher.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.teacher.exception.HomeroomTeacherNotFoundException
import team.msg.sms.domain.teacher.model.HomeroomTeacher
import team.msg.sms.domain.teacher.service.GetHomeroomTeacherService
import team.msg.sms.domain.teacher.spi.HomeroomTeacherPort
import java.util.*
import team.msg.sms.domain.teacher.model.Teacher
import team.msg.sms.domain.user.model.User

@Service
class GetHomeroomTeacherServiceImpl(
    private val homeroomTeacherPort: HomeroomTeacherPort
) : GetHomeroomTeacherService {
    override fun getHomeroomTeacherByUserId(userId: UUID): HomeroomTeacher =
        homeroomTeacherPort.queryHomeroomTeacherByUserId(userId) ?: throw HomeroomTeacherNotFoundException

    override fun getHomeroomTeacherByTeacher(teacher: Teacher, user: User): HomeroomTeacher {
        return homeroomTeacherPort.findHomeroomTeacherByTeacher(teacher, user)
    }
}