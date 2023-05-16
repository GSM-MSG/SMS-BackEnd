package team.msg.sms.domain.auth.dto.response

import java.time.LocalDateTime

data class SignInResponse(
    val accessToken: String,

    val accessTokenExp: LocalDateTime,

    val refreshToken: String,

    val refreshTokenExp: LocalDateTime,

    val isExist: Boolean

)