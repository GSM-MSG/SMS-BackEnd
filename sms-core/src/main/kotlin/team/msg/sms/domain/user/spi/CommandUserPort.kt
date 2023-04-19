package team.msg.sms.domain.user.spi

import team.msg.sms.domain.user.model.User

interface CommandUserPort {
    fun saveUser(user: User): User
}