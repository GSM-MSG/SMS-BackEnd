package team.msg.sms.persistence.user.mapper

import team.msg.sms.domain.user.model.UserFcm
import team.msg.sms.persistence.user.entity.UserFcmJpaEntity
import team.msg.sms.persistence.user.entity.UserJpaEntity

fun UserFcmJpaEntity.toDomain() =
    UserFcm(
        id = id,
        userId = user.id,
        token = token
    )

fun UserFcm.toEntity(
    userJpaEntity: UserJpaEntity
) = UserFcmJpaEntity(
    id = id,
    user = userJpaEntity,
    token = token
)
