package team.msg.sms.domain.student.spi

import team.msg.sms.domain.student.model.StudentTechStack
import java.util.UUID

interface QueryStudentTechStackPort {
    fun queryStudentTechStackByStudentId(studentId: UUID): List<StudentTechStack>
    fun queryStudentTechStack(): List<StudentTechStack>
}