package team.msg.sms.domain.student.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.student.exception.StudentNotFoundException
import team.msg.sms.common.spi.SecurityPort
import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.student.model.StudentTechStack
import team.msg.sms.domain.student.service.GetStudentService
import team.msg.sms.domain.student.spi.StudentPort
import team.msg.sms.domain.techstack.model.TechStack
import team.msg.sms.domain.user.model.User
import java.util.*

@Service
class GetStudentServiceImpl(
    private val studentPort: StudentPort,
    private val securityPort: SecurityPort
) : GetStudentService {
    override fun getStudents(): List<Student.StudentWithUserInfo> =
        studentPort.searchStudent()

    override fun matchStudentWithTechStacks(
        students: List<Student.StudentWithUserInfo>,
        techStacks: List<TechStack>,
        studentTechStacks: List<StudentTechStack>,
        role: String
    ): List<Student.StudentWithUserInfo> {
        return students.map { student ->
            val studentTechStack = studentTechStacks.filter { it.studentId == student.id }
            val matchingTechStacks = studentTechStack.map { techStack ->
                techStacks.find { it.id == techStack.techStackId }!!.stack
            }

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

    override fun getStudentByUserId(uuid: UUID): Student =
        studentPort.queryStudentByUserId(uuid)

    override fun getStudentUserInfoByUuid(uuid: String): Student.StudentWithUserInfo =
        studentPort.queryStudentWithUserInfoById(UUID.fromString(uuid)) ?: throw StudentNotFoundException

    override fun getStudentByUser(user: User): Student =
        studentPort.queryStudentByUser(user)

    override fun currentStudent(): Student.StudentWithUserInfo =
        studentPort.queryStudentUserInfoByUserId(userId = securityPort.getCurrentUserId())
            ?: throw StudentNotFoundException
}