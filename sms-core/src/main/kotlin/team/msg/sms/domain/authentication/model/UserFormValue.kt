package team.msg.sms.domain.authentication.model

import team.msg.sms.common.annotation.Aggregate
import java.util.UUID

@Aggregate
class UserFormValue(
    val id: UUID,
    val authenticationSectionId: UUID,
    val value: String,
    val score: Int,
    val sectionType: SectionType,
    val targetId: UUID
)