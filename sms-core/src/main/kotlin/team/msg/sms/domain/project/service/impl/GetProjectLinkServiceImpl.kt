package team.msg.sms.domain.project.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.project.model.ProjectLink
import team.msg.sms.domain.project.service.GetProjectLinkService
import team.msg.sms.domain.project.spi.ProjectLinkPort

@Service
class GetProjectLinkServiceImpl(
    private val projectLinkPort: ProjectLinkPort
) : GetProjectLinkService {
    override fun getAllByProjectId(projectId: Long): List<ProjectLink> =
        projectLinkPort.queryAllByProjectId(projectId = projectId)
}