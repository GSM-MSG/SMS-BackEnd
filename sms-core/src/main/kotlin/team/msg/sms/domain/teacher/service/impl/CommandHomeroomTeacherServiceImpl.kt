package team.msg.sms.domain.teacher.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.auth.model.Role
import team.msg.sms.domain.teacher.model.HomeroomTeacher
import team.msg.sms.domain.teacher.model.Teacher
import team.msg.sms.domain.teacher.service.CommandHomeroomTeacherService
import team.msg.sms.domain.teacher.spi.HomeroomTeacherPort
import team.msg.sms.domain.user.model.User
import team.msg.sms.domain.user.spi.UserPort

@Service
class CommandHomeroomTeacherServiceImpl(
    private val homeroomTeacherPort: HomeroomTeacherPort,
    private val userPort: UserPort
) : CommandHomeroomTeacherService {
    override fun saveHomeroomTeacher(homeroomTeacher: HomeroomTeacher, teacher: Teacher, user: User) {
        homeroomTeacherPort.saveHomeroomTeacher(homeroomTeacher, teacher, user)

        user.roles.add(Role.ROLE_HOMEROOM)
        userPort.saveUser(user)
    }
}