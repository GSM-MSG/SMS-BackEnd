package team.msg.sms.domain.user.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.auth.model.Role
import team.msg.sms.domain.student.service.StudentService
import team.msg.sms.domain.teacher.service.TeacherService
import team.msg.sms.domain.user.exception.RoleNotExistsException
import team.msg.sms.domain.user.model.User
import team.msg.sms.domain.user.service.CheckUserService
import team.msg.sms.domain.user.spi.QueryUserPort

@Service
class CheckUserServiceImpl(
    private val queryUserPort: QueryUserPort,
    private val studentService: StudentService,
    private val teacherService: TeacherService
) : CheckUserService {
    override fun checkUserExistByEmail(email: String): Boolean =
        queryUserPort.existsUserByEmail(email)

    override fun checkNewUser(user: User): Boolean =
        when (user.roles[0]) {
            Role.ROLE_STUDENT -> studentService.checkNewStudent(user)
            Role.ROLE_TEACHER -> teacherService.checkNewTeacher(user)
            else -> throw RoleNotExistsException
        }
}