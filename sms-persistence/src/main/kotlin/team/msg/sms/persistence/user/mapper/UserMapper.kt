package team.msg.sms.persistence.user.mapper

import team.msg.sms.domain.user.model.User
import team.msg.sms.persistence.user.entity.UserJpaEntity

fun UserJpaEntity.toDomain(): User =
    User(
        id = id,
        name = name,
        email = email,
        roles = roles
    )

fun User.toEntity(): UserJpaEntity =
    UserJpaEntity(
        id = id,
        email = email,
        name = name,
        roles = roles
    )