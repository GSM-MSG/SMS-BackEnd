package team.msg.sms.domain.project.spi

import team.msg.sms.domain.project.model.Project
import team.msg.sms.domain.project.model.ProjectTechStack

interface CommandProjectTechStackPort {
    fun save(projectTechStack: ProjectTechStack)
    fun deleteAllByProjects(projects: List<Project>)
}