package team.msg.sms.domain.student.spi

import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.user.model.User

interface CommandStudentPort {
    fun saveStudent(student: Student, user: User): Student
}