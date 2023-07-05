package team.msg.sms.domain.auth.dto.res

import team.msg.sms.domain.auth.model.Role
import java.time.LocalDateTime

data class SignInWebResponse(
    val accessToken: String,

    val accessTokenExp: LocalDateTime,

    val refreshToken: String,

    val refreshTokenExp: LocalDateTime,

    val role: Role,

    val isExist: Boolean
)