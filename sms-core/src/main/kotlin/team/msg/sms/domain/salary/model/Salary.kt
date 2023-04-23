package team.msg.sms.domain.salary.model

import team.msg.sms.common.annotation.Aggregate

@Aggregate
data class Salary(
    val id: Long,
    val min: Long,
    val max: Long,
    val studentId: Long
)