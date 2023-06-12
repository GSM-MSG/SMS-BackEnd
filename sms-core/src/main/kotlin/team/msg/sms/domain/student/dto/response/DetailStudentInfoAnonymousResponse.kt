package team.msg.sms.domain.student.dto.response

class DetailStudentInfoAnonymousResponse(
    val name: String,
    val introduce: String,
    val major: String,
    val profileImg: String,
    val techStack: List<String>
) {
}