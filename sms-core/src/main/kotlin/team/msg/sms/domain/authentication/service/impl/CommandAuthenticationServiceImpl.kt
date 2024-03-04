package team.msg.sms.domain.authentication.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.authentication.model.Authentication
import team.msg.sms.domain.authentication.service.CommandAuthenticationService
import team.msg.sms.domain.authentication.spi.AuthenticationPort
import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.user.model.User

@Service
class CommandAuthenticationServiceImpl(
    private val authenticationPort: AuthenticationPort
) : CommandAuthenticationService {
    override fun save(
        authentication: Authentication,
        student: Student,
        user: User
    ) = authenticationPort.save(authentication, student, user)
}