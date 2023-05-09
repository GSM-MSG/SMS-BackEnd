package team.msg.sms.domain.region.model

import team.msg.sms.common.annotation.Aggregate
import java.util.UUID

@Aggregate
data class Region(
    val id: UUID,
    val region: String,
    val studentId: UUID
)