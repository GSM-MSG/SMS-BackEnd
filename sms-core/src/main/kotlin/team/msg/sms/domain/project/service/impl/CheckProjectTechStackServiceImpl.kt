package team.msg.sms.domain.project.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.project.model.ProjectTechStack
import team.msg.sms.domain.project.service.CheckProjectTechStackService
import team.msg.sms.domain.techstack.service.TechStackService

@Service
class CheckProjectTechStackServiceImpl(
    private val techStackService: TechStackService
) : CheckProjectTechStackService {
    override fun checkAddedProjectTechStack(
        projects: List<ProjectTechStack>,
        modifyProjects: List<String>
    ): List<String> {
        val techStacks = techStackService.getAllTechStack() // TechStack [(id, stack, count), (id1, stack1, count1)]
        val techStackNames = projects.flatMap { project -> // Projects [(id, projectId, techStackId), (id1, project1, techStack1)]
            techStacks.filter { techStack ->
                project.techStackId == techStack.id
            }.map { it.stack }
        } // stack(spring), stack1(mysql) techStackNames
        // spring, mysql, redis, tdd, swift modifyProjects
        return modifyProjects.filterNot { techStackNames.contains(it) }
    }

    override fun checkRemovedProjectTechStack(projects: List<ProjectTechStack>, modifyProjects: List<String>): List<String> {
        val techStacks = techStackService.getAllTechStack() // TechStack [(id, stack, count), (id1, stack1, count1)]
        val techStackNames = projects.flatMap { project -> // Projects [(id, projectId, techStackId), (id1, project1, techStack1)]
            techStacks.filter { techStack ->
                project.techStackId == techStack.id
            }.map { it.stack }
        }
        return techStackNames.filterNot { modifyProjects.contains(it) }
    }
}