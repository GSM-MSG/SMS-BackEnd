package team.msg.sms.domain.certificate.model

import team.msg.sms.common.annotation.Aggregate
import java.util.*

@Aggregate
data class Certificate(
    val id: Long,
    val certificateName: String,
    val studentId: UUID
)