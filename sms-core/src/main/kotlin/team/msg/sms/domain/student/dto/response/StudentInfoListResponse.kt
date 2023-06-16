package team.msg.sms.domain.student.dto.response


data class StudentInfoListResponse(
    val content: List<MainStudentsResponse>,
    val page: Int,
    val contentSize: Int,
    val totalSize: Long,
    val last: Boolean,
) {
    constructor() : this(arrayListOf(), 0, 0, 0, true)
}

