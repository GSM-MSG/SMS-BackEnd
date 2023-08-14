package team.msg.sms.domain.student.service

import team.msg.sms.domain.student.model.StudentTechStack
import java.util.UUID

interface GetStudentTechStackService {
    fun getStudentTechStackByStudentId(studentId: UUID): List<StudentTechStack>
    fun getStudentTechStack(): List<StudentTechStack>
}