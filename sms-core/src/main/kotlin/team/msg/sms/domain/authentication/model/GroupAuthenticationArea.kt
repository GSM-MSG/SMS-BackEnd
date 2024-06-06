package team.msg.sms.domain.authentication.model

import team.msg.sms.common.annotation.Aggregate
import java.util.UUID

@Aggregate
class GroupAuthenticationArea(
    val id: UUID,
    val title: String,
    val sort: Int,
)