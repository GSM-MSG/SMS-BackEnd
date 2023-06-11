package team.msg.sms.domain.auth.dto.response

import team.msg.sms.domain.auth.model.Role
import java.time.LocalDateTime

data class SignInResponse(
    val accessToken: String,

    val accessTokenExp: LocalDateTime,

    val refreshToken: String,

    val refreshTokenExp: LocalDateTime,

    val role: Role,

    val isExist: Boolean
)