package team.msg.sms.domain.auth.usecase

import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.auth.dto.response.ReIssueTokenResponse
import team.msg.sms.domain.auth.exception.RefreshNotFoundException
import team.msg.sms.domain.auth.spi.JwtPort
import team.msg.sms.domain.auth.spi.RefreshTokenPort
import team.msg.sms.domain.user.exception.InternalServerErrorException
import team.msg.sms.domain.user.service.UserService

@UseCase
class ReIssueTokenUseCase(
    private val refreshTokenPort: RefreshTokenPort,
    private val userService: UserService,
    private val jwtPort: JwtPort
) {
    fun execute(token: String): ReIssueTokenResponse {
        val queryToken = refreshTokenPort.queryRefreshTokenByToken(token)
            ?: throw RefreshNotFoundException

        val user = userService.queryUserById(queryToken.userId)
        val role = user.roles.firstOrNull() ?: throw InternalServerErrorException

        val (accessToken, accessTokenExp, refreshToken, refreshTokenExp) = jwtPort.receiveToken(user.id, role)

        return ReIssueTokenResponse(
            accessToken = accessToken,
            accessTokenExp = accessTokenExp,
            refreshToken = refreshToken,
            refreshTokenExp = refreshTokenExp
        )
    }
}