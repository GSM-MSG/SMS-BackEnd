package team.msg.sms.persistence.student.mapper

import team.msg.sms.domain.auth.model.RefreshToken
import team.msg.sms.domain.student.model.StudentLink
import team.msg.sms.persistence.auth.entity.RefreshTokenEntity
import team.msg.sms.persistence.student.entity.StudentLinkEntity

fun StudentLinkEntity.toDomain(): StudentLink =
    StudentLink(
        token = token,
        studentId = studentId,
        timeToLive = timeToLive
    )

fun StudentLink.toEntity(): StudentLinkEntity =
    StudentLinkEntity(
        token = token,
        studentId = studentId,
        timeToLive = timeToLive
    )
