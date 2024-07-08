package team.msg.sms.persistence.authentication.mapper

import team.msg.sms.domain.authentication.model.AuthenticationFieldGroup
import team.msg.sms.persistence.authentication.entity.AuthenticationFieldGroupJpaEntity

fun AuthenticationFieldGroupJpaEntity.toDomain() =
    AuthenticationFieldGroup(
        id = id,
        maxScore = maxScore,
        sectionId = sectionId
    )

fun AuthenticationFieldGroup.toEntity() =
    AuthenticationFieldGroupJpaEntity(
        id = id,
        maxScore = maxScore,
        sectionId = sectionId
    )