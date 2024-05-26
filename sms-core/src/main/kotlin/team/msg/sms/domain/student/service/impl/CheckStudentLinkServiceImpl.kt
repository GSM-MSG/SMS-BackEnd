package team.msg.sms.domain.student.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.student.service.CheckStudentLinkService
import team.msg.sms.domain.student.spi.StudentLinkPort

@Service
class CheckStudentLinkServiceImpl (
    private val studentLinkPort: StudentLinkPort
) : CheckStudentLinkService {
    override fun checkExistsByToken(token: String): Boolean {
        return studentLinkPort.existsByToken(token)
    }
}