package team.msg.sms.domain.student.service

import team.msg.sms.domain.student.model.Student

interface GetStudentService {
    fun getStudentsWithPage(page: Int, size: Int): Student.StudentWithPageInfo
}