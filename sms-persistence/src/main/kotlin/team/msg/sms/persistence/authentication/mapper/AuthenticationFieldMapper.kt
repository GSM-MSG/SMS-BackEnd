package team.msg.sms.persistence.authentication.mapper

import team.msg.sms.domain.authentication.model.AuthenticationField
import team.msg.sms.persistence.authentication.entity.AuthenticationFieldJpaEntity

fun AuthenticationFieldJpaEntity.toDomain() =
    AuthenticationField(
        id = id,
        description = description,
        placeHolder = placeHolder,
        groupId = groupId,
        fieldInputType = fieldInputType,
        sort = sort
    )

fun AuthenticationField.toEntity() =
    AuthenticationFieldJpaEntity(
        id = id,
        description = description,
        placeHolder = placeHolder,
        groupId = groupId,
        fieldInputType = fieldInputType,
        sort = sort
    )