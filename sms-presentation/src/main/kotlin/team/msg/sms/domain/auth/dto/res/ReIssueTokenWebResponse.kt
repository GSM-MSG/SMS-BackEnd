package team.msg.sms.domain.auth.dto.res

import java.time.LocalDateTime

data class ReIssueTokenWebResponse(
    val accessToken: String,

    val accessTokenExp: LocalDateTime,

    val refreshToken: String,

    val refreshTokenExp: LocalDateTime
)