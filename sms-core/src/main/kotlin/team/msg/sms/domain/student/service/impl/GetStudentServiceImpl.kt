package team.msg.sms.domain.student.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.student.exception.StudentNotFoundException
import team.msg.sms.common.spi.SecurityPort
import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.student.service.GetStudentService
import team.msg.sms.domain.student.spi.StudentPort
import team.msg.sms.domain.techstack.model.TechStack
import java.util.*

@Service
class GetStudentServiceImpl(
    private val studentPort: StudentPort,
    private val securityPort: SecurityPort
) : GetStudentService {
    override fun getStudentsWithPage(page: Int, size: Int): Student.StudentWithPageInfo =
        studentPort.getStudentsWithPage(page, size)

    override fun matchStudentWithTechStacks(
        students: List<Student.StudentWithUserInfo>,
        techStacks: List<TechStack>,
        role: String
    ): List<Student.StudentWithUserInfo> {
        return students.map { student ->
            val matchingTechStacks = techStacks.filter { it.studentId == student.id }.map { it.stack }

            val updatedName = if (role == "ROLE_ANONYMOUS") {
                student.name.replaceRange(1 until student.name.length, "*".repeat(2))
            } else {
                student.name
            }

            student.copy(
                name = updatedName,
                profileImgUrl = if (role == "ROLE_ANONYMOUS") "" else student.profileImgUrl,
                techStack = matchingTechStacks
            )
        }
    }

    override fun getStudentByUuid(uuid: String): Student.StudentWithUserInfo =
        studentPort.queryStudentById(UUID.fromString(uuid)) ?: throw StudentNotFoundException
    override fun currentStudent(): Student =
        studentPort.getStudentByUserId(userId = securityPort.getCurrentUserId())
}