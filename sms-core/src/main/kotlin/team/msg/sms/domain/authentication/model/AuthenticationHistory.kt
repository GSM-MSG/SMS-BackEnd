package team.msg.sms.domain.authentication.model

import team.msg.sms.common.annotation.Aggregate
import java.time.LocalDate
import java.util.*

@Aggregate
class AuthenticationHistory (
    val id: Long,
    val reason: String,
    val activityImages: List<String> = mutableListOf(),
    val activityStatus: ActivityStatus = ActivityStatus.PENDING,
    val teacherId: UUID? = null,
    val authenticationId: UUID,
    val createdAt: LocalDate
)