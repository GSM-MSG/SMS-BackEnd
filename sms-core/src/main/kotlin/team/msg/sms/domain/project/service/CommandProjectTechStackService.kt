package team.msg.sms.domain.project.service

import team.msg.sms.domain.project.model.Project
import team.msg.sms.domain.project.model.ProjectTechStack
import team.msg.sms.domain.techstack.model.TechStack


interface CommandProjectTechStackService {
    fun save(projectTechStack: ProjectTechStack)
    fun deleteAllByProjects(projects: List<Project>)
    fun deleteByProjectIdAndTechStack(projectId: Long, techStack: TechStack)
}