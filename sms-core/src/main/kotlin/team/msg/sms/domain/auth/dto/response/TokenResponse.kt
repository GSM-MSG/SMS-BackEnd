package team.msg.sms.domain.auth.dto.response

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime
data class TokenResponse(
    val accessToken: String,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    val accessTokenExp: LocalDateTime,

    val refreshToken: String,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    val refreshTokenExp: LocalDateTime
)