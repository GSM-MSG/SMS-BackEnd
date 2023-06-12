package team.msg.sms.domain.student.dto.response

import java.util.UUID

class MainStudentsResponse(
    val id: UUID,
    val profileImg: String,
    val major: String,
    val name: String,
    val techStack: List<String>
) {
}