package team.msg.sms.persistence.auth.mapper

import team.msg.sms.domain.auth.model.RefreshToken
import team.msg.sms.persistence.auth.entity.RefreshTokenEntity

fun RefreshTokenEntity.toDomain(): RefreshToken =
    RefreshToken(
        token = token,
        userId = userId
    )

fun RefreshToken.toEntity(): RefreshTokenEntity =
    RefreshTokenEntity(
        token = token,
        userId = userId
    )