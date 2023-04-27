package team.msg.sms.domain.auth.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.auth.exception.RefreshNotFoundException
import team.msg.sms.domain.auth.model.RefreshToken
import team.msg.sms.domain.auth.service.GetAuthService
import team.msg.sms.domain.auth.spi.RefreshTokenPort

@Service
class GetAuthServiceImpl(
    private val refreshTokenPort: RefreshTokenPort
) : GetAuthService {
    override fun getRefreshTokenByToken(token: String): RefreshToken =
        refreshTokenPort.queryRefreshTokenByToken(token) ?: throw RefreshNotFoundException

}