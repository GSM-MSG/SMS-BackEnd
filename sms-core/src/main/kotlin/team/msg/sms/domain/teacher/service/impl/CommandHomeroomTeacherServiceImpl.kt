package team.msg.sms.domain.teacher.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.teacher.model.HomeroomTeacher
import team.msg.sms.domain.teacher.model.Teacher
import team.msg.sms.domain.teacher.service.CommandHomeroomTeacherService
import team.msg.sms.domain.teacher.spi.HomeroomTeacherPort
import team.msg.sms.domain.user.model.User

@Service
class CommandHomeroomTeacherServiceImpl(
    private val homeroomTeacherPort: HomeroomTeacherPort
) : CommandHomeroomTeacherService {
    override fun saveHomeroomTeacher(homeroomTeacher: HomeroomTeacher, teacher: Teacher, user: User) =
        homeroomTeacherPort.saveHomeroomTeacher(homeroomTeacher, teacher, user)
}