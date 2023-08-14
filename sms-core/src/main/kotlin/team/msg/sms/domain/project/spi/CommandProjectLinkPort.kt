package team.msg.sms.domain.project.spi

import team.msg.sms.domain.project.model.Project
import team.msg.sms.domain.project.model.ProjectLink

interface CommandProjectLinkPort {
    fun saveAll(projectLinks: List<ProjectLink>)
    fun deleteAllByProjects(projects: List<Project>)
}