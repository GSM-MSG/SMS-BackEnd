package team.msg.sms.domain.student.service

import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.user.model.User
import java.util.UUID

interface CommandStudentService {
    fun saveStudent(student: Student, user: User): Student
    fun deleteByUuid(studentId: UUID)
}