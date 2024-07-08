package team.msg.sms.persistence.authentication.mapper

import team.msg.sms.domain.authentication.model.MarkingValue
import team.msg.sms.persistence.authentication.entity.MarkingValueJpaEntity

fun MarkingValueJpaEntity.toDomain() =
    MarkingValue(
        id = id,
        score = score,
        type = type,
        setId = setId,
        markingBoardId = markingBoardId,
        createdAt = createdAt,
        createdBy = createdBy
    )

fun MarkingValue.toEntity() =
    MarkingValueJpaEntity(
        id = id,
        score = score,
        type = type,
        setId = setId,
        markingBoardId = markingBoardId,
        createdAt = createdAt,
        createdBy = createdBy
    )