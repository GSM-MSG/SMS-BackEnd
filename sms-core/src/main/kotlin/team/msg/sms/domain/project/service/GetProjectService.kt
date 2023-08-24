package team.msg.sms.domain.project.service

import team.msg.sms.domain.project.model.Project
import java.util.UUID

interface GetProjectService {
    fun getAllProjectByStudentId(studentId: UUID): List<Project>
    fun getMatchingProject(projects: List<Project>, targetProject: Project): Project
}