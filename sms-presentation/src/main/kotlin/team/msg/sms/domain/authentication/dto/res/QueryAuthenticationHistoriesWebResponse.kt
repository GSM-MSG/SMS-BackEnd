package team.msg.sms.domain.authentication.res

import team.msg.sms.domain.authentication.dto.res.QueryAuthenticationHistoryResponseData

data class QueryAuthenticationHistoriesWebResponse(
    val histories: List<QueryAuthenticationHistoryResponseData>
)