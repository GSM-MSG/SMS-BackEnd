package team.msg.sms.domain.student.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.student.service.CommandStudentService
import team.msg.sms.domain.student.spi.StudentPort
import team.msg.sms.domain.user.model.User
import java.util.*

@Service
class CommandStudentServiceImpl(
    private val studentPort: StudentPort
) : CommandStudentService {
    override fun saveStudent(student: Student, user: User): Student =
        studentPort.saveStudent(student, user)

    override fun deleteByUuid(studentId: UUID) =
        studentPort.deleteById(studentId)
}