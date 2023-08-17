package team.msg.sms.domain.project.model

import team.msg.sms.common.annotation.Aggregate
import java.util.UUID

@Aggregate
data class Project(
    val id: Long,
    val title: String,
    val projectIconUrl: String,
    val description: String,
    val myActivity: String?,
    val startDate: String,
    val endDate: String?,
    val studentId: UUID
)
