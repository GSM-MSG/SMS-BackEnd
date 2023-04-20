package team.msg.sms.domain.auth.model

import team.msg.sms.common.annotation.Aggregate
import java.util.UUID

@Aggregate
data class RefreshToken(
    val token: String,
    val userId: UUID
)