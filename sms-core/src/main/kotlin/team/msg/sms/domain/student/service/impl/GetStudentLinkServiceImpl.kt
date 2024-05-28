package team.msg.sms.domain.student.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.student.exception.StudentLinkNotFoundException
import team.msg.sms.domain.student.exception.StudentNotFoundException
import team.msg.sms.domain.student.model.Student.StudentWithUserInfo
import team.msg.sms.domain.student.service.GetStudentLinkService
import team.msg.sms.domain.student.spi.StudentLinkPort
import team.msg.sms.domain.student.spi.StudentPort

@Service
class GetStudentLinkServiceImpl(
    private val studentLinkPort: StudentLinkPort,
    private val studentPort: StudentPort
) : GetStudentLinkService {
    override fun getStudentUserInfoByToken(token: String): StudentWithUserInfo {
        val studentId = studentLinkPort.findStudentIdByToken(token) ?: throw StudentNotFoundException
        val studentWithUserInfo = studentPort.queryStudentWithUserInfoById(studentId) ?: throw StudentLinkNotFoundException

        return studentWithUserInfo
    }
}