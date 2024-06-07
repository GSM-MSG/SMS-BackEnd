package team.msg.sms.persistence.authentication.mapper

import team.msg.sms.domain.authentication.model.GroupAuthenticationArea
import team.msg.sms.persistence.authentication.entity.GroupAuthenticationAreaJpaEntity

fun GroupAuthenticationAreaJpaEntity.toDomain() =
    GroupAuthenticationArea(
        id = id,
        title = title,
        sort = sort,
        authenticationFormId = authenticationFormId
    )

fun GroupAuthenticationArea.toEntity() =
    GroupAuthenticationAreaJpaEntity(
        id = id,
        title = title,
        sort = sort,
        authenticationFormId = authenticationFormId
    )