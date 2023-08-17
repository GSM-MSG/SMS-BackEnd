package team.msg.sms.domain.prize.model

import team.msg.sms.common.annotation.Aggregate
import java.util.UUID

@Aggregate
data class Prize(
    val id: Long,
    val name: String,
    val type: String,
    val date: String,
    val studentId: UUID
)