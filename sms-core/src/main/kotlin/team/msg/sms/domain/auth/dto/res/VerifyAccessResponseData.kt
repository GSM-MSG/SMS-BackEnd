package team.msg.sms.domain.auth.dto.res

data class VerifyAccessResponseData(
    val isExist: Boolean,
    val role: String
)