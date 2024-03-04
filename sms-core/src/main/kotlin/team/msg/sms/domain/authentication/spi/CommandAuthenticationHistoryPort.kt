package team.msg.sms.domain.authentication.spi

import team.msg.sms.domain.authentication.model.Authentication
import team.msg.sms.domain.authentication.model.AuthenticationHistory
import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.user.model.User

interface CommandAuthenticationHistoryPort {
    fun save(
        authenticationHistory: AuthenticationHistory,
        authentication: Authentication,
        student: Student,
        user: User
    ): AuthenticationHistory
}