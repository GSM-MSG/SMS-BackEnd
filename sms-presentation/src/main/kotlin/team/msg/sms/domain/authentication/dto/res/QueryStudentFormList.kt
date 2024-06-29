package team.msg.sms.domain.authentication.dto.res

data class QueryStudentFormList(
    val content: List<UserBoardWithStudentInfoResponseData>,
    val page: Int,
    val totalSize: Long,
    val contentSize: Int,
    val last: Boolean
)
