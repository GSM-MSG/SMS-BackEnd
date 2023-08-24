package team.msg.sms.domain.project.model

import team.msg.sms.common.annotation.Aggregate

@Aggregate
data class ProjectTechStack(
    val id: Long,
    val projectId: Long,
    val techStackId: Long
)