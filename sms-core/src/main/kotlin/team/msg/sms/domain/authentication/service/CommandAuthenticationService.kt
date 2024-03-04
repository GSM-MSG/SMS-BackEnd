package team.msg.sms.domain.authentication.service

import team.msg.sms.domain.authentication.model.Authentication
import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.user.model.User

interface CommandAuthenticationService {
    fun save(
        authentication: Authentication,
        student: Student,
        user: User
    ): Authentication
}