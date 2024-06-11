package team.msg.sms.domain.authentication.dto.res

import java.util.UUID

data class AuthenticationSectionResponseData(
    val sectionId: UUID,
    val section: String,
    val scoreDescription: String,
    val sectionScore: Int,
    val maxCount: Int,
    val fields: List<AuthenticationSectionFieldResponseData>
)
