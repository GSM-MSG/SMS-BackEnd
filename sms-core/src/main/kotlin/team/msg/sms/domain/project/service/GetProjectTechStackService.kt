package team.msg.sms.domain.project.service

import team.msg.sms.domain.project.model.ProjectTechStack

interface GetProjectTechStackService {
    fun getAllByProjectId(projectId: Long): List<ProjectTechStack>
}