package team.msg.sms.domain.authentication.service

import team.msg.sms.domain.authentication.model.Authentication
import java.util.UUID

interface GetAuthenticationService {
    fun getAuthenticationByUuid(uuid: UUID): Authentication
    fun getRequestedAuthentications(): List<Authentication.AuthenticationWithStudentInfoAndRequestedTime>
}