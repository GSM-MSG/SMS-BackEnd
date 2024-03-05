package team.msg.sms.domain.authentication.dto.res

import team.msg.sms.domain.authentication.model.ActivityStatus
import java.time.LocalDate
import java.util.UUID

data class QueryAuthenticationDetailsResponseData (
    val id: UUID,
    val title: String,
    val content: String,
    val activityImages: List<String>,
    val lastModifiedDate: LocalDate,
    val score: Int,
    val activityStatus: ActivityStatus
)