package team.msg.sms.domain.project.spi

import team.msg.sms.domain.project.model.ProjectTechStack

interface QueryProjectTechStackPort {
    fun queryAllByProjectId(projectId: Long): List<ProjectTechStack>
}