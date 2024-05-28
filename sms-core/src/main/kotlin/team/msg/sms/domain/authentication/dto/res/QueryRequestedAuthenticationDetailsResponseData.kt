package team.msg.sms.domain.authentication.dto.res

import java.time.LocalDate
import java.util.*

data class QueryRequestedAuthenticationDetailsResponseData (
    val id: UUID,
    val title: String,
    val content: String,
    val activityImages: List<String>,
    val lastModifiedDate: LocalDate,
    val score: Int
)