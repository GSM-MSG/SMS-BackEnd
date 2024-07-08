package team.msg.sms.domain.authentication.model

import java.util.UUID

data class AuthenticationField(
    val id: UUID,
    val description: String?,
    val placeholder: String?,
    val groupId: UUID,
    val fieldInputType: FieldType,
    val sort: Int
)
