package team.msg.sms.domain.authentication.dto.res

data class QueryRequestedAuthenticationListWebResponse (
    val content: List<RequestedAuthenticationWebResponse>,
    val page: Int,
    val contentSize: Int,
    val totalSize: Long,
    val last: Boolean
)