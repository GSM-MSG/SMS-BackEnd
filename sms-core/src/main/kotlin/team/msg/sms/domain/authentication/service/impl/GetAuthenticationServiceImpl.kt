package team.msg.sms.domain.authentication.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.authentication.exception.AuthenticationNotFoundException
import team.msg.sms.domain.authentication.model.Authentication
import team.msg.sms.domain.authentication.service.GetAuthenticationService
import team.msg.sms.domain.authentication.spi.QueryAuthenticationPort
import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.user.model.User
import java.util.UUID

@Service
class GetAuthenticationServiceImpl(
    private val queryAuthenticationPort: QueryAuthenticationPort
) : GetAuthenticationService {
    override fun getAuthenticationByUuid(uuid: UUID): Authentication =
        queryAuthenticationPort.queryAuthenticationByUuid(uuid) ?: throw AuthenticationNotFoundException

    override fun getRequestedAuthentications(): List<Authentication.AuthenticationWithStudentInfoAndRequestedTime> =
        queryAuthenticationPort.queryRequestedAuthentications()

    override fun getAuthenticationByStudent(student: Student, user: User): List<Authentication> =
        queryAuthenticationPort.queryMyAuthenticationByStudent(student, user)
}