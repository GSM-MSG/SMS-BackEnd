package team.msg.sms.domain.user.service

import team.msg.sms.domain.user.model.User
import java.util.UUID

interface CommandUserService {
    fun createUserWhenNotExistUser(existUser: Boolean, user: User): User
    fun deleteByUuid(userId: UUID)
}