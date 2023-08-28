package team.msg.sms.common.util

import team.msg.sms.domain.file.model.Image
import team.msg.sms.domain.file.service.ImageService
import team.msg.sms.domain.project.dto.res.ProjectInProgressResponseData
import team.msg.sms.domain.project.dto.res.ProjectLinkResponseData
import team.msg.sms.domain.project.dto.res.ProjectResponseData
import team.msg.sms.domain.project.model.Project
import team.msg.sms.domain.project.model.ProjectLink
import team.msg.sms.domain.project.model.ProjectTechStack
import team.msg.sms.domain.project.service.ProjectLinkService
import team.msg.sms.domain.project.service.ProjectTechStackService
import team.msg.sms.domain.techstack.model.TechStack

object ProjectUtil {

    fun generateProjectResponseData(
        imageService: ImageService,
        projectLinkService: ProjectLinkService,
        projectTechStackService: ProjectTechStackService,
        projects: List<Project>,
        techStacks: List<TechStack>
    ) : List<ProjectResponseData> =
        projects.map {
            val image = imageService.getAllByProjectId(projectId = it.id)
            val link = projectLinkService.getAllByProjectId(projectId = it.id)
            val projectTechStack = projectTechStackService.getAllByProjectId(projectId = it.id)
            toProjectResponseData(
                project = it,
                projectLink = link,
                projectImage = image,
                projectTechStack = projectTechStack,
                techStack = techStacks
            )
        }

    private fun toProjectTechStacks(techStacks: List<TechStack>, projectTechStack: ProjectTechStack): TechStack? =
        techStacks.find { it.id == projectTechStack.techStackId }

    private fun toProjectResponseData(
        project: Project,
        projectLink: List<ProjectLink>,
        projectImage: List<Image>,
        projectTechStack: List<ProjectTechStack>,
        techStack: List<TechStack>
    ): ProjectResponseData =
        ProjectResponseData(
            description = project.description,
            icon = project.projectIconUrl,
            inProgress = toInProgressResponseData(project.startDate, project.endDate),
            links = projectLink.map { toLinkResponseData(it) },
            myActivity = project.myActivity,
            previewImages = projectImage.map { it.imageUrl },
            techStacks = projectTechStack.map {
                toProjectTechStacks(techStack, it)?.stack ?: ""
            },
            name = project.title
        )


    private fun toLinkResponseData(projectLink: ProjectLink) =
        ProjectLinkResponseData(
            name = projectLink.name,
            url = projectLink.url
        )

    private fun toInProgressResponseData(start: String, end: String?) =
        ProjectInProgressResponseData(
            start = start,
            end = end
        )
}