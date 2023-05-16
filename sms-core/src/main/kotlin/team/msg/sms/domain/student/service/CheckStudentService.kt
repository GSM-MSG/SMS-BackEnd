package team.msg.sms.domain.student.service

import team.msg.sms.domain.user.model.User
import java.util.UUID

interface CheckStudentService {
    fun checkStudentExistsByUserId(userId: UUID)
    fun checkNewStudent(user: User, role: String): Boolean
}