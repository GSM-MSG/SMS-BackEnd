package team.msg.sms.domain.authentication.dto.req

import java.util.UUID

data class ApproveAuthenticationRequestData (
    val score: Int,
    val reason: String,
)