package team.msg.sms.domain.student.dto.response

import team.msg.sms.domain.student.model.Student


data class StudentInfoListResponse(
    val content: List<Student.StudentWithUserInfo>,
    val page: Int,
    val last: Boolean,
    val size: Int,
) {
}

