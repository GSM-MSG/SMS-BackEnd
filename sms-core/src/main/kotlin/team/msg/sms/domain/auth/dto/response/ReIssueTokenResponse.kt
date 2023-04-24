package team.msg.sms.domain.auth.dto.response

import java.time.LocalDateTime

data class ReIssueTokenResponse(
    val accessToken: String,

    val accessTokenExp: LocalDateTime,

    val refreshToken: String,

    val refreshTokenExp: LocalDateTime
)