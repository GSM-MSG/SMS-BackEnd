package team.msg.sms.domain.user.service

import team.msg.sms.domain.user.model.User

interface CheckUserService {
    fun checkUserExistByEmail(email: String): Boolean
}