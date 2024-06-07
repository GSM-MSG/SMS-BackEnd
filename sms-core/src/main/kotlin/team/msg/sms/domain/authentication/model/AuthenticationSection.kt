package team.msg.sms.domain.authentication.model

import team.msg.sms.common.annotation.Aggregate
import java.util.UUID

@Aggregate
class AuthenticationSection(
    val id: UUID,
    val groupId: UUID,
    val sectionName: String,
    val description: String,
    val placeHolder: String?,
    val sectionType: SectionType,
    val sectionScore: Int,
    val maxCount: Int
)