package team.msg.sms.domain.student.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.student.model.StudentTechStack
import team.msg.sms.domain.student.service.CommandStudentTechStackService
import team.msg.sms.domain.student.spi.StudentTechStackPort

@Service
class CommandStudentTechStackServiceImpl(
    private val studentTechStackPort: StudentTechStackPort
) : CommandStudentTechStackService {
    override fun save(studentTechStack: StudentTechStack) =
        studentTechStackPort.save(studentTechStack)

    override fun deleteAllByStudent(student: Student) =
        studentTechStackPort.deleteAllByStudent(student)
}