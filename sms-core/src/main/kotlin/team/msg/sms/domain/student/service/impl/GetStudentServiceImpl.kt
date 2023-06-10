package team.msg.sms.domain.student.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.student.service.GetStudentService
import team.msg.sms.domain.student.spi.StudentPort
import team.msg.sms.domain.techstack.model.TechStack

@Service
class GetStudentServiceImpl(
    private val studentPort: StudentPort
) : GetStudentService {
    override fun getStudentsWithPage(page: Int, size: Int): Student.StudentWithPageInfo =
        studentPort.getStudentsWithPage(page, size)

    override fun matchStudentWithTechStacks(students: List<Student.StudentWithUserInfo>, techStacks: List<TechStack>): List<Student.StudentWithUserInfo> {
        return students.map { student ->
            val matchingTechStacks = techStacks
                .filter { techStack -> techStack.studentId == student.id }
                .map { techStack -> techStack.stack }

            student.copy(techStack = matchingTechStacks)
        }
    }
}