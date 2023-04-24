package team.msg.sms.domain.auth.dto.response

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime
import java.time.ZonedDateTime

data class SignInResponse(
    val accessToken: String,

    val accessTokenExp: LocalDateTime,

    val refreshToken: String,

    val refreshTokenExp: LocalDateTime

)