package team.msg.sms.domain.authentication.dto.res

data class UserBoardPageResponseData(
    val content: List<UserBoardWithStudentInfoResponseData>,
    val page: Int,
    val contentSize: Int,
    val last: Boolean,
    val totalSize: Long
)
