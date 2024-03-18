package team.msg.sms.domain.authentication.spi

import team.msg.sms.domain.authentication.dto.req.ApproveAuthenticationRequestData
import team.msg.sms.domain.authentication.model.Authentication
import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.user.model.User
import java.util.*

interface CommandAuthenticationPort {
    fun save(
        authentication: Authentication,
        student: Student,
        user: User
    ): Authentication

    fun deleteAuthenticationByUuid(uuid: UUID)
}