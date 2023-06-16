package team.msg.sms.domain.student.dto.res

import java.util.UUID

class MainStudentsResponseData(
    val id: UUID,
    val profileImg: String,
    val major: String,
    val name: String,
    val techStack: List<String>
)