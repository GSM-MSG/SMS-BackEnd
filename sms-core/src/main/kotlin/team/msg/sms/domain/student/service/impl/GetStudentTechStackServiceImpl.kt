package team.msg.sms.domain.student.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.student.model.StudentTechStack
import team.msg.sms.domain.student.service.GetStudentTechStackService
import team.msg.sms.domain.student.spi.StudentTechStackPort
import team.msg.sms.domain.techstack.model.TechStack
import java.util.*

@Service
class GetStudentTechStackServiceImpl(
    private val studentTechStackPort: StudentTechStackPort
) : GetStudentTechStackService {
    override fun getStudentTechStackByStudentId(studentId: UUID): List<StudentTechStack> =
        studentTechStackPort.queryStudentTechStackByStudentId(studentId)

    override fun getStudentTechStack(): List<StudentTechStack> =
        studentTechStackPort.queryStudentTechStack()

    override fun getMatchTechStackNameWithId(studentTechStacks: List<StudentTechStack>, techStacks: List<TechStack>): List<String> =
        studentTechStacks
            .mapNotNull { studentTechStack ->
                techStacks.find { it.id == studentTechStack.techStackId }?.stack
            }
}