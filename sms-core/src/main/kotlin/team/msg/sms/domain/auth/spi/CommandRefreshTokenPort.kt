package team.msg.sms.domain.auth.spi

import team.msg.sms.domain.auth.model.RefreshToken

interface CommandRefreshTokenPort {
    fun saveRefreshToken(refreshToken: RefreshToken): RefreshToken
}