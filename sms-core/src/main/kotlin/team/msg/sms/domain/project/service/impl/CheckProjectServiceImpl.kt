package team.msg.sms.domain.project.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.project.model.Project
import team.msg.sms.domain.project.service.CheckProjectService

@Service
class CheckProjectServiceImpl(

) : CheckProjectService {
    override fun checkAddedProject(projects: List<Project>, modifyProject: Project): Project {
        return projects.find { existingProject ->
            existingProject.run {
                title == modifyProject.title &&
                        description == modifyProject.description &&
                        projectIconUrl == modifyProject.projectIconUrl &&
                        myActivity == modifyProject.myActivity &&
                        startDate == modifyProject.startDate &&
                        endDate == modifyProject.endDate
            }
        } ?: modifyProject
    }

    override fun checkRemovedProject(projects: List<Project>, modifyProjects: List<Project>): List<Project> {
        return projects.filterNot { existingProject ->
            modifyProjects.any { modifyProject ->
                existingProject.run {
                    title == modifyProject.title &&
                            description == modifyProject.description &&
                            projectIconUrl == modifyProject.projectIconUrl &&
                            myActivity == modifyProject.myActivity &&
                            startDate == modifyProject.startDate &&
                            endDate == modifyProject.endDate
                }
            }
        }
    }
}