package team.msg.sms.domain.auth.dto.response

data class VerifyAccessResponseData(
    val isExist: Boolean,
    val role: String
)
