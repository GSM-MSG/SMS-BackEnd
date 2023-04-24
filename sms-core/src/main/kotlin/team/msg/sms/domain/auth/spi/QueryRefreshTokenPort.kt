package team.msg.sms.domain.auth.spi

import team.msg.sms.domain.auth.model.RefreshToken

interface QueryRefreshTokenPort {
    fun queryRefreshTokenByToken(token: String): RefreshToken?
}