package team.msg.sms.domain.student.dto.req

data class SignUpRequestData(
    val major: String,
    val techStacks: List<String>,
    val profileImgUrl: String,
    val introduce: String,
    val portfolioUrl: String,
    val contactEmail: String,
)