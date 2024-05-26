package team.msg.sms.persistence.teacher.mapper

import team.msg.sms.domain.teacher.model.Teacher
import team.msg.sms.persistence.teacher.entity.TeacherJpaEntity
import team.msg.sms.persistence.user.entity.UserJpaEntity

fun Teacher.toEntity(user: UserJpaEntity): TeacherJpaEntity =
    TeacherJpaEntity(
        id = id,
        user = user
    )

fun TeacherJpaEntity.toDomain(): Teacher =
    Teacher(
        id = id,
        userId = user.id
    )