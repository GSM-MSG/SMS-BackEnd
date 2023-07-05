package team.msg.sms.domain.student.service

import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.techstack.model.TechStack
import team.msg.sms.domain.user.model.User

interface GetStudentService {
    fun getStudentsWithPage(page: Int, size: Int): Student.StudentWithPageInfo

    fun matchStudentWithTechStacks(
        students: List<Student.StudentWithUserInfo>,
        techStacks: List<TechStack>,
        role: String
    ): List<Student.StudentWithUserInfo>

    fun getStudentByUuid(uuid: String): Student.StudentWithUserInfo
    fun getStudentByUser(user: User): Student
    fun currentStudent(): Student.StudentWithUserInfo
}