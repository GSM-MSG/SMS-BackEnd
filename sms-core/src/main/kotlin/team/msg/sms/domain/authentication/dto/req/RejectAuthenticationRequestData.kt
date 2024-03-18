package team.msg.sms.domain.authentication.dto.req

data class RejectAuthenticationRequestData (
    val score: Int,
    val reason: String,
)