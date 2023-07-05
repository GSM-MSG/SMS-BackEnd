package team.msg.sms.domain.student.service

import team.msg.sms.domain.student.dto.req.FiltersRequestData
import team.msg.sms.domain.student.model.Student

interface FilterStudentService {
    fun filterStudents(students: List<Student.StudentWithUserInfo>, filters: FiltersRequestData, role: String): List<Student.StudentWithUserInfo>
}