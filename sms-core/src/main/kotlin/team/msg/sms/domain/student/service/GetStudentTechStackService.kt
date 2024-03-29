package team.msg.sms.domain.student.service

import team.msg.sms.domain.student.model.StudentTechStack
import team.msg.sms.domain.techstack.model.TechStack
import java.util.UUID

interface GetStudentTechStackService {
    fun getStudentTechStackByStudentId(studentId: UUID): List<StudentTechStack>
    fun getStudentTechStack(): List<StudentTechStack>
    fun getMatchTechStackNameWithId(studentTechStacks: List<StudentTechStack>, techStacks: List<TechStack>): List<String>
}