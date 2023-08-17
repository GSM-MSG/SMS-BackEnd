package team.msg.sms.domain.project.service

import team.msg.sms.domain.project.model.ProjectLink

interface GetProjectLinkService {
    fun getAllByProjectId(projectId: Long): List<ProjectLink>
}