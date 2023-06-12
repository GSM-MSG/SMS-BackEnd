package team.msg.sms.domain.student.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.student.exception.StudentAlreadyException
import team.msg.sms.domain.student.service.CheckStudentService
import team.msg.sms.domain.student.spi.StudentPort
import team.msg.sms.domain.user.exception.RoleNotExistsException
import team.msg.sms.domain.user.model.User

@Service
class CheckStudentServiceImpl (
    private val studentPort: StudentPort
) : CheckStudentService{
    override fun checkStudentExistsByUser(user: User) {
        if(studentPort.existsStudentByUser(user))
            throw StudentAlreadyException
    }

    override fun checkNewStudent(user: User, role: String): Boolean {
        return when (role) {
            "ROLE_STUDENT" -> studentPort.existsStudentByUser(user)
            "ROLE_TEACHER" -> true
            else -> throw RoleNotExistsException
        }
    }
}