package team.msg.sms.domain.student.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.student.model.StudentTechStack
import team.msg.sms.domain.student.service.GetStudentTechStackService
import team.msg.sms.domain.student.spi.StudentTechStackPort
import java.util.*

@Service
class GetStudentTechStackServiceImpl(
    private val studentTechStackPort: StudentTechStackPort
) : GetStudentTechStackService {
    override fun getStudentTechStackByStudentId(studentId: UUID): List<StudentTechStack> =
        studentTechStackPort.queryStudentTechStackByStudentId(studentId)

    override fun getStudentTechStack(): List<StudentTechStack> =
        studentTechStackPort.queryStudentTechStack()
}