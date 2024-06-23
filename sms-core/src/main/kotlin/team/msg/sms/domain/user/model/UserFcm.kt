package team.msg.sms.domain.user.model

import java.util.UUID

data class UserFcm(
    val id: Long,
    val userId: UUID,
    val token: String
)
