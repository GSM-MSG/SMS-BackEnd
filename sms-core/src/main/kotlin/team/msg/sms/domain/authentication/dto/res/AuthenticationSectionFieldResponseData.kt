package team.msg.sms.domain.authentication.dto.res

import team.msg.sms.domain.authentication.model.SectionType
import java.util.*

data class AuthenticationSectionFieldResponseData(
    val key: UUID,
    val type: SectionType,
    val values: List<AuthenticationSelectorValueResponseData>?,
    val example: String?
)
