package team.msg.sms.domain.region.model

import team.msg.sms.common.annotation.Aggregate

@Aggregate
data class Region(
    val id: Long,
    val region: String,
    val studentId: Long
)