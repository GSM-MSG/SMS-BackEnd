package team.msg.sms.domain.project.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.project.model.ProjectLink
import team.msg.sms.domain.project.service.CheckProjectLinkService

@Service
class CheckProjectLinkServiceImpl(

) : CheckProjectLinkService {
    override fun checkAddedProjectLink(
        projectLinks: List<ProjectLink>,
        modifyProjectLinks: List<ProjectLink>
    ): List<ProjectLink> {
        return modifyProjectLinks.filterNot { modifiedProjectLink ->
            projectLinks.any { existingProjectLink ->
                existingProjectLink.name == modifiedProjectLink.name &&
                        existingProjectLink.url == modifiedProjectLink.url &&
                        existingProjectLink.projectId == modifiedProjectLink.projectId
            }
        }
    }

    override fun checkRemovedProjectLink(projectLinks: List<ProjectLink>, modifyProjectLinks: List<ProjectLink>): List<ProjectLink> {
        return projectLinks.filterNot { existingProjectLink ->
            modifyProjectLinks.any { modifiedProjectLink ->
                modifiedProjectLink.name == existingProjectLink.name &&
                        modifiedProjectLink.url == existingProjectLink.url &&
                        modifiedProjectLink.projectId == existingProjectLink.projectId
            }
        }
    }
}