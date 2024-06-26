package team.msg.sms.persistence.authentication.mapper

import team.msg.sms.domain.authentication.model.AuthenticationForm
import team.msg.sms.persistence.authentication.entity.AuthenticationFormJpaEntity

fun AuthenticationFormJpaEntity.toDomain() =
    AuthenticationForm(
        id = id,
        title = title,
        version = version,
        createdBy = createdBy,
        createdAt = createdAt,
        active = active
    )

fun AuthenticationForm.toEntity() =
    AuthenticationFormJpaEntity(
        id = id,
        title = title,
        version = version,
        createdBy = createdBy,
        createdAt = createdAt,
        active = active
    )