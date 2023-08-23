package team.msg.sms.domain.user.usecase

import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.user.model.User
import team.msg.sms.domain.user.service.UserService
import java.util.UUID

@UseCase
class QueryUserByUserIdUseCase(
    private val userService: UserService
) {
    fun execute(userId: UUID): User? =
        userService.getUserById(userId)
}