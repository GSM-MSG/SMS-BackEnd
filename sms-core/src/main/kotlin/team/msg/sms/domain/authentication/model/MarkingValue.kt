package team.msg.sms.domain.authentication.model

import java.time.LocalDateTime
import java.util.UUID

data class MarkingValue(
    val id: UUID,
    val score: Double,
    val type: MarkingType,
    val fieldId: UUID,
    val groupId: UUID,
    val createdAt: LocalDateTime,
    val createdBy: UUID
)
