package team.msg.sms.domain.student.service

import team.msg.sms.domain.user.model.User

interface CheckStudentService {
    fun checkStudentExistsByUser(user: User)
    fun studentExistsByUser(user: User): Boolean
    fun checkNewStudent(user: User, role: String): Boolean
}