package team.msg.sms.domain.authentication.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.authentication.model.Authentication
import team.msg.sms.domain.authentication.service.CommandAuthenticationHistoryService
import team.msg.sms.domain.authentication.spi.CommandAuthenticationHistoryPort
import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.user.model.User

@Service
class CommandAuthenticationHistoryServiceImpl(
    private val commandAuthenticationHistoryPort: CommandAuthenticationHistoryPort
) : CommandAuthenticationHistoryService {
    override fun deleteAuthenticationHistoryByAuthentication(
            authentication: Authentication,
            student: Student,
            user: User
    ) {
        commandAuthenticationHistoryPort.deleteAuthenticationHistory(authentication, student, user)
    }
}