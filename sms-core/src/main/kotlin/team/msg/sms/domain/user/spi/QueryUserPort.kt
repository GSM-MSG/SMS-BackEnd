package team.msg.sms.domain.user.spi

import team.msg.sms.domain.user.model.User
import java.util.UUID

interface QueryUserPort {
    fun queryUserByEmail(email: String): User?

    fun queryUserById(id: UUID): User?

    fun existsUserByEmail(email: String): Boolean

    fun existsUserById(uuid: UUID): Boolean
}