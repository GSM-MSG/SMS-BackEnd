package team.msg.sms.domain.project.spi

import team.msg.sms.domain.project.model.ProjectLink

interface QueryProjectLinkPort {
    fun queryAllByProjectId(projectId: Long): List<ProjectLink>
}