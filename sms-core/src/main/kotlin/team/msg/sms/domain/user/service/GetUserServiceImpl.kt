package team.msg.sms.domain.user.service

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.auth.model.Role
import team.msg.sms.domain.user.model.User
import team.msg.sms.domain.user.spi.QueryUserPort

@Service
class GetUserServiceImpl(
    private val queryUserPort: QueryUserPort
) : GetUserService {
    override fun getRoleByGAuthInfo(email: String, role: String): Role {
        val user = queryUserPort.queryUserByEmail(email) ?: return when (role) {
            "ROLE_STUDENT" -> Role.STUDENT
            "ROLE_TEACHER" -> Role.TEACHER
            else -> throw RuntimeException("존재하지 않는 권한 인 Exception 호출")
        }
        return user.roles.first()
    }

    override fun queryUserByEmail(email: String): User =
        queryUserPort.queryUserByEmail(email) ?: throw RuntimeException("존재하지 않는 이메일 입니다.")
}