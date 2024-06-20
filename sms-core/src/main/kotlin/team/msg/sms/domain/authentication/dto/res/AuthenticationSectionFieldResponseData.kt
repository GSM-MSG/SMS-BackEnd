package team.msg.sms.domain.authentication.dto.res

import team.msg.sms.domain.authentication.model.FieldType
import java.util.*

data class AuthenticationSectionFieldResponseData(
    val fieldId: UUID,
    val fieldType: FieldType,
    val scoreDescription: String?,
    val values: List<AuthenticationSelectorValueResponseData>?,
    val example: String?
)
