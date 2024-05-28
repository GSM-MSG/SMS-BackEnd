package team.msg.sms.domain.user.service

import team.msg.sms.domain.auth.model.Role
import team.msg.sms.domain.user.model.User
import java.util.UUID

interface CommandUserService {
    fun createUserWhenNotExistUser(existUser: Boolean, user: User): User
    fun updateStuNum(user: User, stuNum: String): User
    fun saveRoles(user: User, role: List<Role>): User
    fun deleteByUuid(userId: UUID)
}