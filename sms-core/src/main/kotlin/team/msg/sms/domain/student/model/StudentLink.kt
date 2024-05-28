package team.msg.sms.domain.student.model

import team.msg.sms.common.annotation.Aggregate
import java.util.UUID

@Aggregate
data class StudentLink(
    val token: String,
    val studentId: UUID,
    val timeToLive: Long
)
