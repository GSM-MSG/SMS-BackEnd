package team.msg.sms.domain.authentication.dto.res

data class QueryRequestedAuthenticationListResponseData (
    val content: List<RequestedAuthenticationResponseData>,
    val page: Int,
    val contentSize: Int,
    val totalSize: Long,
    val last: Boolean
)