package team.msg.sms.domain.student.dto.res


data class StudentInfoListResponseData(
    val content: List<MainStudentsResponseData>,
    val page: Int,
    val contentSize: Int,
    val totalSize: Long,
    val last: Boolean,
) {
    constructor(): this(arrayListOf(), 0, 0, 0, true)
}
