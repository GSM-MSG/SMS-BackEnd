package team.msg.sms.persistence.authentication.mapper

import team.msg.sms.domain.authentication.model.SelectorSectionValue
import team.msg.sms.persistence.authentication.entity.SelectorSectionValueJpaEntity

fun SelectorSectionValue.toEntity() = SelectorSectionValueJpaEntity(
    id = id,
    authenticationFieldId = authenticationFieldId,
    name = name,
    sort = sort
)

fun SelectorSectionValueJpaEntity.toDomain() = SelectorSectionValue(
    id = id,
    authenticationFieldId = authenticationFieldId,
    name = name,
    sort = sort
)