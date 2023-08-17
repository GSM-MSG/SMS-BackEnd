package team.msg.sms.persistence.project.mapper

import team.msg.sms.domain.project.model.ProjectLink
import team.msg.sms.persistence.project.entity.ProjectJpaEntity
import team.msg.sms.persistence.project.entity.ProjectLinkJpaEntity

fun ProjectLink.toEntity(
    projectJpaEntity: ProjectJpaEntity
) =
    ProjectLinkJpaEntity(
        name = name,
        url = url,
        project = projectJpaEntity
    )

fun ProjectLinkJpaEntity.toDomain() =
    ProjectLink(
        id = id,
        name = name,
        url = url,
        projectId = project.id
    )
