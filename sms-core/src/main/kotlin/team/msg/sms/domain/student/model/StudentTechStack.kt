package team.msg.sms.domain.student.model

import team.msg.sms.common.annotation.Aggregate
import java.util.UUID

@Aggregate
data class StudentTechStack(
    val id: Long,
    val studentId: UUID,
    val techStackId: Long
)
