package team.msg.sms.persistence.authentication.mapper

import team.msg.sms.domain.authentication.model.AuthenticationHistory
import team.msg.sms.persistence.authentication.entity.AuthenticationHistoryJpaEntity
import team.msg.sms.persistence.authentication.entity.AuthenticationJpaEntity
import team.msg.sms.persistence.teacher.entity.TeacherJpaEntity

fun AuthenticationHistory.toEntity(
    authentication: AuthenticationJpaEntity,
    teacher: TeacherJpaEntity? = null
) = AuthenticationHistoryJpaEntity(
    id = id,
    reason = reason,
    activityStatus = activityStatus,
    teacher = teacher,
    authentication = authentication
)

fun AuthenticationHistoryJpaEntity.toDomain() = AuthenticationHistory(
    id = id,
    reason = reason,
    activityStatus = activityStatus,
    teacherId = teacher?.id,
    authenticationId = authentication.id
)