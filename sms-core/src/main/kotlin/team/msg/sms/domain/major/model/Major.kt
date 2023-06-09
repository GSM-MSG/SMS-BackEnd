package team.msg.sms.domain.major.model

import team.msg.sms.common.annotation.Aggregate

@Aggregate
class Major(
    val id: Long,
    val major: String,
    val isDefaultMajor: Boolean
)