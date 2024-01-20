package team.msg.sms.domain.teacher.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.teacher.service.CheckTeacherService
import team.msg.sms.domain.teacher.spi.TeacherPort
import team.msg.sms.domain.user.model.User

@Service
class CheckTeacherServiceImpl(
    private val teacherPort: TeacherPort
) : CheckTeacherService {
    override fun checkNewTeacher(user: User) =
        teacherPort.existsTeacherByUser(user)
}