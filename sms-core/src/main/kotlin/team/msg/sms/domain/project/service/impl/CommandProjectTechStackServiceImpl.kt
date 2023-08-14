package team.msg.sms.domain.project.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.project.model.Project
import team.msg.sms.domain.project.model.ProjectTechStack
import team.msg.sms.domain.project.service.CommandProjectTechStackService
import team.msg.sms.domain.project.spi.ProjectTechStackPort

@Service
class CommandProjectTechStackServiceImpl(
    private val projectTechStackPort: ProjectTechStackPort
) : CommandProjectTechStackService {
    override fun save(projectTechStack: ProjectTechStack) =
        projectTechStackPort.save(projectTechStack)

    override fun deleteAllByProjects(projects: List<Project>) =
        projectTechStackPort.deleteAllByProjects(projects)
}