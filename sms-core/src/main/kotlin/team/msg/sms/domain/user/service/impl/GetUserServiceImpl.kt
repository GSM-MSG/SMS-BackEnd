package team.msg.sms.domain.user.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.common.spi.SecurityPort
import team.msg.sms.domain.auth.model.Role
import team.msg.sms.domain.user.exception.InternalServerErrorException
import team.msg.sms.domain.user.exception.RoleNotExistsException
import team.msg.sms.domain.user.exception.UserNotFoundException
import team.msg.sms.domain.user.model.User
import team.msg.sms.domain.user.service.GetUserService
import team.msg.sms.domain.user.spi.QueryUserPort
import java.util.*

@Service
class GetUserServiceImpl(
    private val queryUserPort: QueryUserPort,
    private val securityPort: SecurityPort
) : GetUserService {
    override fun getRoleByGAuthInfo(email: String, role: String): Role {
        val user = queryUserPort.queryUserByEmail(email) ?: return when (role) {
            "ROLE_STUDENT" -> Role.ROLE_STUDENT
            "ROLE_TEACHER" -> Role.ROLE_TEACHER
            else -> throw RoleNotExistsException
        }
        return user.roles.firstOrNull() ?: throw InternalServerErrorException
    }

    override fun queryUserByEmail(email: String): User =
        queryUserPort.queryUserByEmail(email) ?: throw UserNotFoundException

    override fun queryUserById(id: UUID): User =
        queryUserPort.queryUserById(id) ?: throw UserNotFoundException

    override fun getCurrentUser(): User =
        queryUserById(securityPort.getCurrentUserId())
}