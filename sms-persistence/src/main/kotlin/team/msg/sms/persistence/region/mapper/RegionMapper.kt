package team.msg.sms.persistence.region.mapper

import team.msg.sms.domain.region.model.Region
import team.msg.sms.persistence.region.entity.RegionJpaEntity
import team.msg.sms.persistence.student.entity.StudentJpaEntity


fun RegionJpaEntity.toDomain(): Region =
    Region(
        id = id,
        region = region,
        studentId = student.id
    )

fun Region.toEntity(
    student: StudentJpaEntity
): RegionJpaEntity =
    RegionJpaEntity(
        region = region,
        student = student
    )