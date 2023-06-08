package team.msg.sms.domain.student.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.student.service.GetStudentService
import team.msg.sms.domain.student.spi.StudentPort

@Service
class GetStudentServiceImpl(
    private val studentPort: StudentPort
) : GetStudentService {
    override fun getStudentsWithPage(page: Int, size: Int): Student.StudentWithPageInfo =
        studentPort.getStudentsWithPage(page, size)
}