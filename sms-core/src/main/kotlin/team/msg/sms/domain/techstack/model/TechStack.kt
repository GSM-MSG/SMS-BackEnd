package team.msg.sms.domain.techstack.model

import team.msg.sms.common.annotation.Aggregate
import java.util.*

@Aggregate
data class TechStack(
    val id: Long,
    val stack: String,
    val studentId: UUID
)
