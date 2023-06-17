package team.msg.sms.domain.auth.usecase

import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.auth.dto.res.ReIssueTokenResponseData
import team.msg.sms.domain.auth.exception.RefreshNotFoundException
import team.msg.sms.domain.auth.model.RefreshToken
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
    fun execute(token: String): ReIssueTokenResponseData {
        val queryToken = refreshTokenPort.queryRefreshTokenByToken(token)
            ?: throw RefreshNotFoundException

        val user = userService.getUserById(queryToken.userId)
        val role = user.roles.firstOrNull() ?: throw InternalServerErrorException

        refreshTokenPort.deleteRefreshToken(queryToken)

        val (accessToken, accessTokenExp, refreshToken, refreshTokenExp) = jwtPort.receiveToken(user.id, role)

        refreshTokenPort.saveRefreshToken(
            refreshToken = RefreshToken(
                token = refreshToken,
                userId = user.id
            )
        )

        return ReIssueTokenResponseData(
            accessToken = accessToken,
            accessTokenExp = accessTokenExp,
            refreshToken = refreshToken,
            refreshTokenExp = refreshTokenExp
        )
    }
}