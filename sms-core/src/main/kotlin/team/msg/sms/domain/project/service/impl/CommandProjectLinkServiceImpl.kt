package team.msg.sms.domain.project.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.project.model.Project
import team.msg.sms.domain.project.model.ProjectLink
import team.msg.sms.domain.project.service.CommandProjectLinkService
import team.msg.sms.domain.project.spi.ProjectLinkPort

@Service
class CommandProjectLinkServiceImpl(
private val projectLinkPort: ProjectLinkPort
) : CommandProjectLinkService {
    override fun saveAll(projectLinks: List<ProjectLink>) =
        projectLinkPort.saveAll(projectLinks)


    override fun deleteAllByProjects(projects: List<Project>) =
        projectLinkPort.deleteAllByProjects(projects = projects)

    override fun deleteByProjectLink(projectLink: ProjectLink, project: Project) {
        projectLinkPort.deleteByProjectLink(projectLink, project)
    }
}