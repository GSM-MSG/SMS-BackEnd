package team.msg.sms.domain.student.dto.res

import java.util.UUID

data class MainStudentsWebResponse(
    val id: UUID,
    val profileImg: String,
    val major: String,
    val name: String,
    val techStacks: List<String>
)