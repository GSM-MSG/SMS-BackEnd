package team.msg.sms.domain.user.service

interface CheckUserService {
    fun checkUserExistByEmail(email: String): Boolean
}