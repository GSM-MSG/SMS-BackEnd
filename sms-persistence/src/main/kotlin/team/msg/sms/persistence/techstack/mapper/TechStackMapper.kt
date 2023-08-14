package team.msg.sms.persistence.techstack.mapper

import team.msg.sms.domain.techstack.model.TechStack
import team.msg.sms.persistence.techstack.entity.TechStackJpaEntity

fun TechStackJpaEntity.toDomain() =
    TechStack(
        id = id,
        stack = stack,
        count = count
    )

fun TechStack.toEntity(
) =
    TechStackJpaEntity(
        id = id,
        stack = stack,
        count = count
    )