package team.msg.sms.domain.user.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.auth.model.Role
import team.msg.sms.domain.user.exception.UserNotFoundException
import team.msg.sms.domain.user.model.User
import team.msg.sms.domain.user.service.CommandUserService
import team.msg.sms.domain.user.spi.UserPort
import java.util.*

@Service
class CommandUserServiceImpl(
    private val userPort: UserPort
) : CommandUserService {
    override fun createUserWhenNotExistUser(existUser: Boolean, user: User): User {
        return if(existUser) {
            userPort.queryUserByEmail(user.email) ?: throw UserNotFoundException
        } else {
            userPort.saveUser(user)!!
        }
    }

    override fun saveRoles(user: User, role: List<Role>): User {
        user.roles.addAll(role)
        return userPort.saveUser(user)
    }

    override fun deleteByUuid(userId: UUID) =
        userPort.deleteByUuid(userId)
}