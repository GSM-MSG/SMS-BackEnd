package team.msg.sms.domain.authentication.dto.res

import java.util.UUID

data class AuthenticationFieldGroupResponseData(
    val groupId: UUID,
    val maxScore: Double,
    val fields: List<AuthenticationSectionFieldResponseData>
)
