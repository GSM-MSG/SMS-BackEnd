package team.msg.sms.domain.teacher.model

import team.msg.sms.common.annotation.Aggregate
import java.util.UUID

@Aggregate
data class Teacher (
    val id: UUID,
    val userId: UUID,
)