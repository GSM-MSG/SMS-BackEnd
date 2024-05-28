package team.msg.sms.domain.authentication.dto.req

data class ApproveAuthenticationRequestData (
    val score: Int,
    val reason: String,
)