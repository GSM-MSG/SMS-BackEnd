package team.msg.sms.persistence.prize.mapper

import team.msg.sms.domain.prize.model.Prize
import team.msg.sms.persistence.prize.entity.PrizeJpaEntity
import team.msg.sms.persistence.student.entity.StudentJpaEntity

fun PrizeJpaEntity.toDomain() =
    Prize(
        id = id,
        name = name,
        type = type,
        date = date,
        studentId = student.id
    )

fun Prize.toEntity(
    studentJpaEntity: StudentJpaEntity
) =
    PrizeJpaEntity(
        id = id,
        name = name,
        type = type,
        date = date,
        student = studentJpaEntity
    )