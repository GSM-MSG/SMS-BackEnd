package team.msg.sms.domain.user.spi

import team.msg.sms.domain.user.model.User
import java.util.UUID

interface CommandUserPort {
    fun saveUser(user: User): User
    fun deleteByUuid(userId: UUID)
}