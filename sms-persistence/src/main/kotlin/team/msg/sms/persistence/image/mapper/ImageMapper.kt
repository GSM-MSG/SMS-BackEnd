package team.msg.sms.persistence.image.mapper

import team.msg.sms.domain.file.model.Image
import team.msg.sms.persistence.image.entity.ImageJpaEntity
import team.msg.sms.persistence.project.entity.ProjectJpaEntity

fun Image.toEntity(
    project: ProjectJpaEntity
): ImageJpaEntity =
    ImageJpaEntity(
        imageUrl = imageUrl,
        project = project
    )

fun ImageJpaEntity.toDomain() =
    Image(
        id = id,
        imageUrl = imageUrl,
        projectId = project.id
    )