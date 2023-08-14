package team.msg.sms.domain.project.model

import team.msg.sms.common.annotation.Aggregate

@Aggregate
data class ProjectLink(
    val id: Long,
    val name: String,
    val url: String,
    val projectId: Long
)
