package team.msg.sms.domain.authentication.res

import team.msg.sms.domain.authentication.model.ActivityStatus
import java.time.LocalDate
import java.util.*

data class QueryAuthenticationDetailsWebResponse(
    val id: UUID,
    val title: String,
    val content: String,
    val activityImages: List<String>,
    val lastModifiedDate: LocalDate,
    val score: Int,
    val activityStatus: ActivityStatus
)
