package team.msg.sms.domain.authentication.dto.req

import team.msg.sms.domain.authentication.model.SectionType
import java.util.*

data class SubmitUserFormRequestData(
    val authenticationSectionId: UUID,
    val value:String?,
    val sectionType: SectionType,
    val targetId: UUID?
)