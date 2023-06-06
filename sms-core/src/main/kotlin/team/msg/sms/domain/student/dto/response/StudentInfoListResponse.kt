package team.msg.sms.domain.student.dto.response


data class StudentInfoListResponse(
    val content: List<StudentContentInfo>,
    val page: Int,
    val last: Boolean,
    val size: Int,
) {
    data class StudentContentInfo(
        val profileImg: String,
        val name: String,
        val major: String,
        val techStack: List<String>
    )
}

