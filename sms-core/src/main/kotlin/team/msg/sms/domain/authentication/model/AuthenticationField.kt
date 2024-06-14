package team.msg.sms.domain.authentication.model

import java.util.UUID

data class AuthenticationField(
    val id: UUID,
    val sectionId: UUID,
    val description: String?,
    val placeHolder: String?,
    val fieldInputType: SectionType,
    val fieldScore: Double,
    val sort: Int
)
