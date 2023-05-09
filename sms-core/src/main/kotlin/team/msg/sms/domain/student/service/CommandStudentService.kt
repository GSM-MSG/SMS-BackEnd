package team.msg.sms.domain.student.service

import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.user.model.User

interface CommandStudentService {
    fun saveStudent(student: Student, user: User): Student
}