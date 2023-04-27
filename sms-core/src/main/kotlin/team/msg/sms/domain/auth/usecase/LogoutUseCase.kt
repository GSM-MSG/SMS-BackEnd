package team.msg.sms.domain.auth.usecase

import team.msg.sms.common.annotation.UseCase
import team.msg.sms.common.service.SecurityService
import team.msg.sms.domain.auth.service.AuthService
import team.msg.sms.domain.user.exception.UserNotFoundException

@UseCase
class LogoutUseCase(
    private val securityService: SecurityService,
    private val authService: AuthService
) {
    fun execute(refreshToken: String) {
        val userId = securityService.getCurrentUserId()
        val token = authService.getRefreshTokenByToken(refreshToken)
        if (token.userId == userId) authService.deleteRefreshToken(token) else throw UserNotFoundException
    }

}