package team.msg.sms.persistence.project.mapper

import team.msg.sms.domain.project.model.ProjectTechStack
import team.msg.sms.persistence.project.entity.ProjectJpaEntity
import team.msg.sms.persistence.project.entity.ProjectTechStackJpaEntity
import team.msg.sms.persistence.techstack.entity.TechStackJpaEntity

fun ProjectTechStackJpaEntity.toDomain() =
    ProjectTechStack(
        id = this.id,
        projectId = this.project.id,
        techStackId = this.techStack.id
    )

fun ProjectTechStack.toEntity(projectJpaEntity: ProjectJpaEntity, techStackJpaEntity: TechStackJpaEntity) =
    ProjectTechStackJpaEntity(
        project = projectJpaEntity,
        techStack = techStackJpaEntity
    )