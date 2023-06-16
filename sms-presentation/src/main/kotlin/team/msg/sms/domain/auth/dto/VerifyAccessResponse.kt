package team.msg.sms.domain.auth.dto

data class VerifyAccessResponse(
    val isExist: Boolean,
    val role: String
)