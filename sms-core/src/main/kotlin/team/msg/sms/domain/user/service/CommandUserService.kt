package team.msg.sms.domain.user.service

import team.msg.sms.domain.user.model.User

interface CommandUserService {
    fun createUserWhenNotExistUser(existUser: Boolean, user: User): User
}