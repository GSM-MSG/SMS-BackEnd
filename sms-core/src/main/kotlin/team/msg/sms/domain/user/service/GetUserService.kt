package team.msg.sms.domain.user.service

import team.msg.sms.domain.auth.model.Role
import team.msg.sms.domain.user.model.User
import java.util.UUID

interface GetUserService {
    fun getRoleByGAuthInfo(email: String, role: String): Role

    fun getUserByEmail(email: String): User

    fun getUserById(id: UUID): User

    fun getCurrentUser(): User
}