package team.msg.sms.domain.student.dto.response

import team.msg.sms.domain.student.model.Student


data class StudentInfoListResponse(
    val content: List<MainStudentsResponse>,
    val page: Int,
    val contentSize: Int,
    val totalSize: Long,
    val last: Boolean,
) {
}

