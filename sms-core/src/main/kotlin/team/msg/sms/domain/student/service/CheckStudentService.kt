package team.msg.sms.domain.student.service

import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.user.model.User

interface CheckStudentService {
    fun checkStudentExistsByUser(user: User)
    fun studentExistsByUser(user: User): Boolean
    fun checkNewStudent(user: User): Boolean
    fun checkStudentDataMismatch(student: Student, modifyStudentData: Student): Boolean
}