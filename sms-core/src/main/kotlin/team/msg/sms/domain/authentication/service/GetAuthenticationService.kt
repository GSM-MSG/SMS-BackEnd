package team.msg.sms.domain.authentication.service

import team.msg.sms.domain.authentication.model.Authentication
import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.user.model.User
import java.util.UUID

interface GetAuthenticationService {
    fun getAuthenticationByUuid(uuid: UUID): Authentication
    fun getRequestedAuthentications(): List<Authentication.AuthenticationWithStudentInfoAndRequestedTime>
    fun getAuthenticationByStudent(student: Student, user: User): List<Authentication>
}