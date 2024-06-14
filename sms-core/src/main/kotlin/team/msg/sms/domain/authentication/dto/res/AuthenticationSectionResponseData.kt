package team.msg.sms.domain.authentication.dto.res

import java.util.UUID

data class AuthenticationSectionResponseData(
    val sectionId: UUID,
    val sectionName: String,
    val maxCount: Int,
    val fields: List<AuthenticationSectionFieldResponseData>
)
