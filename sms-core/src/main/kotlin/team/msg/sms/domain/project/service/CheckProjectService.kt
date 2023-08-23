package team.msg.sms.domain.project.service

import team.msg.sms.domain.project.model.Project

interface CheckProjectService {
    fun checkAddedProject(projects: List<Project>, modifyProject: Project): Project?
    fun checkRemovedProject(projects: List<Project>, modifyProjects: List<Project>): List<Project>
}