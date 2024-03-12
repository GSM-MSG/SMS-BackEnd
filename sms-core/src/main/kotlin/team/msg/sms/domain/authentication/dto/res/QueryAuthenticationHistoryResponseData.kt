package team.msg.sms.domain.authentication.dto.res

import team.msg.sms.domain.authentication.model.ActivityStatus
import java.time.LocalDate

data class QueryAuthenticationHistoryResponseData (
    val authenticationId: String,
    val teacherName: String?,
    val gradedDate: LocalDate,
    val score: Int,
    val activityStatus: ActivityStatus,
    val reason: String,
)