package team.msg.sms.domain.authentication.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.authentication.model.Authentication
import team.msg.sms.domain.authentication.model.AuthenticationHistory
import team.msg.sms.domain.authentication.service.GetAuthenticationHistoryService
import team.msg.sms.domain.authentication.spi.QueryAuthenticationHistoryPort
import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.user.model.User

@Service
class GetAuthenticationHistoryServiceImpl(
    private val queryAuthenticationHistoryPort: QueryAuthenticationHistoryPort
) : GetAuthenticationHistoryService{
    override fun getLatestAuthenticationHistory(
        authentication: Authentication,
        student: Student,
        user: User
    ): AuthenticationHistory =
        queryAuthenticationHistoryPort.queryLatestAuthenticationHistory(authentication, student, user)

    override fun getAuthenticationHistories(
        authentication: Authentication,
        student: Student,
        user: User
    ): List<AuthenticationHistory> =
        queryAuthenticationHistoryPort.queryAuthenticationHistories(authentication, student, user)
}