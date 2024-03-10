package team.msg.sms.domain.authentication.dto.res

data class QueryAuthenticationHistoriesResponseData (
    val histories: List<QueryAuthenticationHistoryResponseData>
)