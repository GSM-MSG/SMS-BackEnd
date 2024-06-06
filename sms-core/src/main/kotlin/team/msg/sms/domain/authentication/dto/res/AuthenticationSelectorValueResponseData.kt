package team.msg.sms.domain.authentication.dto.res

import java.util.UUID

data class AuthenticationSelectorValueResponseData(
    val selectId: UUID,
    val value: String
)
