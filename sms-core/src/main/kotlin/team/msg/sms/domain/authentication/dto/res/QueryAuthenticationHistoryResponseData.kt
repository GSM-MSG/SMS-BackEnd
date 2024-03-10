package team.msg.sms.domain.authentication.dto.res

data class QueryAuthenticationHistoryResponseData (
    val authenticationId: String,
    val teacherName: String?,
    val gradedDate: String,
    val score: Int,
    val activityStatus: String,
    val reason: String,
)