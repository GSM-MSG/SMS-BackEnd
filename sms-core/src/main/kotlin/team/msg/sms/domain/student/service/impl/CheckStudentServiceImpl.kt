package team.msg.sms.domain.student.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.student.exception.StudentAlreadyException
import team.msg.sms.domain.student.service.CheckStudentService
import team.msg.sms.domain.student.spi.StudentPort
import java.util.*

@Service
class CheckStudentServiceImpl (
    private val studentPort: StudentPort
) : CheckStudentService{
    override fun checkStudentExistsByUserId(userId: UUID) {
        if(studentPort.existsStudentByUserId(userId))
            throw StudentAlreadyException
    }
}