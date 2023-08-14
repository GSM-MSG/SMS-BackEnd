package team.msg.sms.domain.project.service

import team.msg.sms.domain.project.model.Project
import team.msg.sms.domain.project.model.ProjectTechStack


interface CommandProjectTechStackService {
    fun save(projectTechStack: ProjectTechStack)
    fun deleteAllByProjects(projects: List<Project>)
}