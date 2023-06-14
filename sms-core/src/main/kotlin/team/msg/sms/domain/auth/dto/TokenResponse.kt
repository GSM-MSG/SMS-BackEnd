package team.msg.sms.domain.auth.dto

import java.time.LocalDateTime

data class TokenResponse(
    val accessToken: String,

    val accessTokenExp: LocalDateTime,

    val refreshToken: String,

    val refreshTokenExp: LocalDateTime
)