package team.msg.sms.domain.user.service

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.auth.model.Role
import team.msg.sms.domain.user.exception.RoleNotExistsException
import team.msg.sms.domain.user.exception.UserNotFoundException
import team.msg.sms.domain.user.model.User
import team.msg.sms.domain.user.spi.QueryUserPort

@Service
class GetUserServiceImpl(
    private val queryUserPort: QueryUserPort
) : GetUserService {
    override fun getRoleByGAuthInfo(email: String, role: String): Role {
        val user = queryUserPort.queryUserByEmail(email) ?: return when ("role") {
            "ROLE_STUDENT" -> Role.STUDENT
            "ROLE_TEACHER" -> Role.TEACHER
            else -> throw RoleNotExistsException
        }
        return user.roles.first()
    }

    override fun queryUserByEmail(email: String): User =
        queryUserPort.queryUserByEmail(email) ?: throw UserNotFoundException
}