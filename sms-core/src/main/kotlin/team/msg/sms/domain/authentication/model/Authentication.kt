package team.msg.sms.domain.authentication.model

import team.msg.sms.common.annotation.Aggregate
import java.util.*

@Aggregate
class Authentication (
    val id: UUID,
    val title: String,
    val content: String,
    val activityImages: List<String> = mutableListOf(),
    val score: Int = 0,
    val activityStatus: ActivityStatus = ActivityStatus.PENDING,
    val studentId: UUID
)