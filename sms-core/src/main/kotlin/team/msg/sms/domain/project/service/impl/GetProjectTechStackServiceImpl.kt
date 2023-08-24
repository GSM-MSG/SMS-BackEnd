package team.msg.sms.domain.project.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.project.model.ProjectTechStack
import team.msg.sms.domain.project.service.GetProjectTechStackService
import team.msg.sms.domain.project.spi.ProjectTechStackPort

@Service
class GetProjectTechStackServiceImpl(
    private val projectTechStackPort: ProjectTechStackPort
) : GetProjectTechStackService {
    override fun getAllByProjectId(projectId: Long): List<ProjectTechStack> =
        projectTechStackPort.queryAllByProjectId(projectId = projectId)
}