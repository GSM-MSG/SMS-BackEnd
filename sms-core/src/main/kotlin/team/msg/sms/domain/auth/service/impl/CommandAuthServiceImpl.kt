package team.msg.sms.domain.auth.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.auth.model.RefreshToken
import team.msg.sms.domain.auth.service.CommandAuthService
import team.msg.sms.domain.auth.spi.CommandRefreshTokenPort

@Service
class CommandAuthServiceImpl(
    private val commandRefreshTokenPort: CommandRefreshTokenPort
) : CommandAuthService {
    override fun deleteRefreshToken(refreshToken: RefreshToken) =
        commandRefreshTokenPort.deleteRefreshToken(refreshToken)
}