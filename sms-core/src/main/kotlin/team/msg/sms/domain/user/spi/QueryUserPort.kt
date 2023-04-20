package team.msg.sms.domain.user.spi

import team.msg.sms.domain.user.model.User

interface QueryUserPort {
    fun queryUserByEmail(email: String): User?

    fun existsUserByEmail(email: String): Boolean
}