package team.msg.sms.domain.student.dto.res


data class StudentInfoListWebResponse(
    val content: List<MainStudentsResponseData>,
    val page: Int,
    val contentSize: Int,
    val totalSize: Long,
    val last: Boolean,
)
