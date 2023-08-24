package team.msg.sms.domain.file.model

import team.msg.sms.common.annotation.Aggregate

@Aggregate
data class Image(
    val id: Long,
    val imageUrl: String,
    val projectId: Long
)