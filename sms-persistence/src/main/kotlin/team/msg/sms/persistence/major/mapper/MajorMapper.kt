package team.msg.sms.persistence.major.mapper

import team.msg.sms.domain.major.model.Major
import team.msg.sms.persistence.major.entity.MajorJpaEntity

fun Major.toEntity() =
    MajorJpaEntity(
        major = major,
        isDefaultMajor = isDefaultMajor
    )

fun MajorJpaEntity.toDomain() =
    Major(
        id = id,
        major = major,
        isDefaultMajor = isDefaultMajor
    )