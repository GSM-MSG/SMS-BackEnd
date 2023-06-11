package team.msg.sms.domain.student.service

import team.msg.sms.domain.student.dto.request.FiltersData
import team.msg.sms.domain.student.model.Student

interface FilterStudentService {
    fun filterStudents(students: List<Student.StudentWithUserInfo>,filters: FiltersData, role: String): List<Student.StudentWithUserInfo>
}