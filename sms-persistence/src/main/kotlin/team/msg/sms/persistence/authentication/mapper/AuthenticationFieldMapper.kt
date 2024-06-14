package team.msg.sms.persistence.authentication.mapper

import team.msg.sms.domain.authentication.model.AuthenticationField
import team.msg.sms.persistence.authentication.entity.AuthenticationFieldJpaEntity

fun AuthenticationFieldJpaEntity.toDomain() =
    AuthenticationField(
        id = id,
        sectionId = sectionId,
        description = description,
        placeHolder = placeHolder,
        fieldInputType = fieldInputType,
        fieldScore = fieldScore,
        sort = sort
    )

fun AuthenticationField.toEntity() =
    AuthenticationFieldJpaEntity(
        id = id,
        sectionId = sectionId,
        description = description,
        placeHolder = placeHolder,
        fieldInputType = fieldInputType,
        fieldScore = fieldScore,
        sort = sort
    )