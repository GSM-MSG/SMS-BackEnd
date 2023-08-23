package team.msg.sms.persistence.image

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.msg.sms.domain.file.model.Image
import team.msg.sms.domain.file.spi.ImagePort
import team.msg.sms.domain.project.exception.ProjectNotFoundException
import team.msg.sms.domain.project.model.Project
import team.msg.sms.persistence.image.mapper.toDomain
import team.msg.sms.persistence.image.mapper.toEntity
import team.msg.sms.persistence.image.repository.ImageJpaRepository
import team.msg.sms.persistence.project.repository.ProjectJpaRepository

@Component
class ImagePersistenceAdapter(
    private val imageJpaRepository: ImageJpaRepository,
    private val projectJpaRepository: ProjectJpaRepository
) : ImagePort {
    override fun saveAll(images: List<Image>) {
        val project = projectJpaRepository.findByIdOrNull(images.first().projectId)
            ?: throw ProjectNotFoundException
        imageJpaRepository.saveAll(images
            .map {
                it.toEntity(project)
            })
    }


    override fun deleteAllByProjects(projects: List<Project>) {
        imageJpaRepository.deleteAllByProjects(
            projects = projects.map {
                it.id
            })
    }

    override fun deleteByImage(image: Image, project: Project) {
        val project = projectJpaRepository.findByIdOrNull(project.id)
            ?: throw ProjectNotFoundException
        imageJpaRepository.delete(image.toEntity(project))
    }

    override fun queryAllByProjectId(projectId: Long): List<Image> =
        imageJpaRepository.findAllByProjectId(projectId)
            .map {
                it.toDomain()
            }
}