package team.msg.sms.domain.project.service

import team.msg.sms.domain.project.model.ProjectLink

interface CheckProjectLinkService {
    fun checkAddedProjectLink(projectLinks: List<ProjectLink>, modifyProjectLinks: List<ProjectLink>): List<ProjectLink>
    fun checkRemovedProjectLink(projectLinks: List<ProjectLink>, modifyProjectLinks: List<ProjectLink>): List<ProjectLink>
}