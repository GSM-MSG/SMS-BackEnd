package team.msg.sms.persistence.project

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.msg.sms.domain.project.exception.ProjectNotFoundException
import team.msg.sms.domain.project.model.Project
import team.msg.sms.domain.project.model.ProjectTechStack
import team.msg.sms.domain.project.spi.ProjectTechStackPort
import team.msg.sms.domain.student.exception.StudentNotFoundException
import team.msg.sms.domain.techstack.model.TechStack
import team.msg.sms.persistence.project.mapper.toDomain
import team.msg.sms.persistence.project.mapper.toEntity
import team.msg.sms.persistence.project.repository.ProjectJpaRepository
import team.msg.sms.persistence.project.repository.ProjectTechStackJpaRepository
import team.msg.sms.persistence.student.entity.StudentJpaEntity
import team.msg.sms.persistence.student.repository.StudentJpaRepository
import team.msg.sms.persistence.techstack.mapper.toEntity
import team.msg.sms.persistence.techstack.repository.TechStackJpaRepository

@Component
class ProjectTechStackPersistenceAdapter(
    private val projectTechStackRepository: ProjectTechStackJpaRepository,
    private val projectRepository: ProjectJpaRepository,
    private val techStackRepository: TechStackJpaRepository
) : ProjectTechStackPort {
    override fun save(projectTechStack: ProjectTechStack) {
        val project = projectRepository.findByIdOrNull(projectTechStack.projectId)
            ?: throw ProjectNotFoundException
        val techStack = techStackRepository.findByIdOrNull(projectTechStack.techStackId)
        projectTechStackRepository.save(projectTechStack.toEntity(project, techStack!!))
    }

    override fun deleteAllByProjects(projects: List<Project>) {
        projectTechStackRepository.deleteAllByProjects(
            projects
                .map {
                    it.id
                }
        )
    }

    override fun deleteByProjectIdAndTechStack(projectId: Long, techStack: TechStack) {
        val projectJpaEntity = (projectRepository.findByIdOrNull(projectId)
            ?: throw ProjectNotFoundException)
        val techStackJpaEntity = techStack.toEntity()
        projectTechStackRepository.deleteByProjectAndTechStack(projectJpaEntity, techStackJpaEntity)
    }

    override fun queryAllByProjectId(projectId: Long): List<ProjectTechStack> =
        projectTechStackRepository.findAllByProjectId(projectId = projectId)
            .map {
                it.toDomain()
            }
}