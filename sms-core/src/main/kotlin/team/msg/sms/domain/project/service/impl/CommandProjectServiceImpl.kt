package team.msg.sms.domain.project.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.project.model.Project
import team.msg.sms.domain.project.service.CommandProjectService
import team.msg.sms.domain.project.spi.ProjectPort
import team.msg.sms.domain.student.model.Student

@Service
class CommandProjectServiceImpl(
    private val projectPort: ProjectPort
) : CommandProjectService {
    override fun save(project: Project): Project =
        projectPort.save(project)

    override fun saveOrUpdateProject(student: Student, project: Project): Project {
        if (project.id != 0L) {
            val existingProject = projectPort.queryOneByProject(project)
            val updatedProject = existingProject.copy(
                title = project.title,
                description = project.description,
                projectIconUrl = project.projectIconUrl,
                myActivity = project.myActivity,
                startDate = project.startDate,
                endDate = project.endDate
            )
            return projectPort.save(updatedProject)
        }

        return projectPort.save(project)
    }

    override fun deleteAllByStudent(student: Student) =
        projectPort.deleteAllByStudent(student)

    override fun deleteByProject(project: Project, student: Student) {
        projectPort.deleteByProject(project, student)
    }
}