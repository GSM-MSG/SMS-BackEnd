package team.msg.sms.domain.authentication.model

import team.msg.sms.common.annotation.Aggregate
import java.util.*

@Aggregate
class SelectorSectionValue(
    val id: UUID,
    val authenticationSectionId: UUID,
    val name: String,
    val sort: Int
)