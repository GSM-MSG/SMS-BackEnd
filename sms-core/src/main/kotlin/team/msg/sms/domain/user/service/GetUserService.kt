package team.msg.sms.domain.user.service

import team.msg.sms.domain.auth.model.Role
import team.msg.sms.domain.user.model.User

interface GetUserService {
    fun getRoleByGAuthInfo(email: String, role: String): Role

    fun queryUserByEmail(email: String): User
}