package team.msg.sms.domain.authentication.model

import team.msg.sms.common.annotation.Aggregate
import java.time.LocalDateTime
import java.util.UUID

@Aggregate
class UserFormValue(
    val id: UUID,
    val setId: UUID?,
    val authenticationFieldGroupId: UUID,
    val authenticationSectionId: UUID,
    val authenticationFieldId: UUID,
    val value: String?,
    val score: Int,
    val fieldType: FieldType,
    val targetId: UUID?,
    val createdBy: UUID,
    val createdAt: LocalDateTime,
    val authenticationFormId: UUID
)