package team.msg.sms.domain.auth.model

import team.msg.sms.common.annotation.Aggregate

@Aggregate
data class RefreshToken(
    val token: String,
    val userId: Long
)