package team.msg.sms.domain.student.service

import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.techstack.model.TechStack

interface GetStudentService {
    fun getStudentsWithPage(page: Int, size: Int): Student.StudentWithPageInfo

    fun matchStudentWithTechStacks(
        students: List<Student.StudentWithUserInfo>,
        techStacks: List<TechStack>,
        role: String
    ): List<Student.StudentWithUserInfo>

    fun getStudnetByUuid(uuid: String): Student.StudentWithUserInfo
}