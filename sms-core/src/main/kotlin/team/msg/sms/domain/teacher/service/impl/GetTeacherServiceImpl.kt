package team.msg.sms.domain.teacher.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.teacher.model.Teacher
import team.msg.sms.domain.teacher.service.GetTeacherService
import team.msg.sms.domain.teacher.spi.TeacherPort
import team.msg.sms.domain.user.model.User

@Service
class GetTeacherServiceImpl(
    private val teacherPort: TeacherPort
) : GetTeacherService {
    override fun getTeacherByUser(user: User): Teacher {
        return teacherPort.findTeacherByUser(user)
    }
}