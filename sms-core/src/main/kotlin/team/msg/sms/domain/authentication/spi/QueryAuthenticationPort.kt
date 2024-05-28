package team.msg.sms.domain.authentication.spi

import team.msg.sms.domain.authentication.model.Authentication
import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.user.model.User
import java.util.UUID

interface QueryAuthenticationPort {
    fun queryAuthenticationByUuid(uuid: UUID): Authentication?
    fun queryRequestedAuthentications(): List<Authentication.AuthenticationWithStudentInfoAndRequestedTime>

    fun queryMyAuthenticationByStudent(student: Student, user: User): List<Authentication>
}