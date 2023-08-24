package team.msg.sms.domain.project.service

import team.msg.sms.domain.project.model.ProjectTechStack

interface CheckProjectTechStackService {
    fun checkAddedProjectTechStack(projects: List<ProjectTechStack>, modifyProjects: List<String>): List<String>
    fun checkRemovedProjectTechStack(projects: List<ProjectTechStack>, modifyProjects: List<String>): List<String>
}