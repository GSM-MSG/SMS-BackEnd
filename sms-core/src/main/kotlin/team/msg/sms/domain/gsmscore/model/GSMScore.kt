package team.msg.sms.domain.gsmscore.model

import team.msg.sms.common.annotation.Aggregate

@Aggregate
data class GSMScore(
    val id: Long,
    val score: Number,
    val scoreFileUrl: String,
    val studentId: Long
)