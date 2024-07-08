package team.msg.sms.domain.authentication.dto.req

import java.util.UUID

data class GradingRequestData(
    val setId: UUID,
    val score: Double?
)
