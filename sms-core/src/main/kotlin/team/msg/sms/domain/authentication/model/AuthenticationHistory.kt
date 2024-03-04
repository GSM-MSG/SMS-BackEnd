package team.msg.sms.domain.authentication.model

import team.msg.sms.common.annotation.Aggregate
import java.time.LocalDate
import java.util.*

@Aggregate
class AuthenticationHistory (
    val id: Long = 0,
    val reason: String,
    val activityStatus: ActivityStatus,
    val teacherId: UUID? = null,
    val authenticationId: UUID,
    val createdAt: LocalDate = LocalDate.now()
)