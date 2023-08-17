package team.msg.sms.persistence.project

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.msg.sms.domain.project.exception.ProjectNotFoundException
import team.msg.sms.domain.project.model.Project
import team.msg.sms.domain.project.model.ProjectLink
import team.msg.sms.domain.project.spi.ProjectLinkPort
import team.msg.sms.persistence.project.mapper.toDomain
import team.msg.sms.persistence.project.mapper.toEntity
import team.msg.sms.persistence.project.repository.ProjectJpaRepository
import team.msg.sms.persistence.project.repository.ProjectLinkJpaRepository

@Component
class ProjectLinkPersistenceAdapter(
    val projectLinkJpaRepository: ProjectLinkJpaRepository,
    val projectJpaRepository: ProjectJpaRepository
) : ProjectLinkPort {
    override fun saveAll(projectLinks: List<ProjectLink>) {
        val project = projectJpaRepository.findByIdOrNull(projectLinks[0].projectId)
            ?: throw ProjectNotFoundException
        projectLinkJpaRepository.saveAll(
            projectLinks
                .map {
                    it.toEntity(project)
                }
        )
    }

    override fun deleteAllByProjects(projects: List<Project>) {
        projectLinkJpaRepository.deleteAllByProjects(
            projects
                .map {
                    it.id
                }
        )
    }

    override fun queryAllByProjectId(projectId: Long): List<ProjectLink> =
        projectLinkJpaRepository.findAllByProjectId(projectId = projectId)
            .map {
                it.toDomain()
            }
}