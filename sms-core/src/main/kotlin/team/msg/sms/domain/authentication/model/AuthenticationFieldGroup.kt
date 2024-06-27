package team.msg.sms.domain.authentication.model

import java.util.UUID

data class AuthenticationFieldGroup(
    val id: UUID,
    val maxScore: Double,
    val sectionId: UUID,
)
