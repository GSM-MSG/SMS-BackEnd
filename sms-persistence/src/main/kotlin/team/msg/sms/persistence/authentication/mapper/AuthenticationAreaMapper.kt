package team.msg.sms.persistence.authentication.mapper

import team.msg.sms.domain.authentication.model.AuthenticationArea
import team.msg.sms.persistence.authentication.entity.AuthenticationAreaJpaEntity

fun AuthenticationAreaJpaEntity.toDomain() =
    AuthenticationArea(
        id = id,
        title = title,
        sort = sort,
        authenticationFormId = authenticationFormId
    )

fun AuthenticationArea.toEntity() =
    AuthenticationAreaJpaEntity(
        id = id,
        title = title,
        sort = sort,
        authenticationFormId = authenticationFormId
    )