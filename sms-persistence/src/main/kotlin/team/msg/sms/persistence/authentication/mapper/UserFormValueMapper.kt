package team.msg.sms.persistence.authentication.mapper

import team.msg.sms.domain.authentication.model.UserFormValue
import team.msg.sms.persistence.authentication.entity.UserFormValueJpaEntity

fun UserFormValueJpaEntity.toDomain() =
    UserFormValue(
        id = id,
        authenticationSectionId = authenticationSectionId,
        value = value,
        score = score,
        sectionType = sectionType,
        targetId = targetId
    )

fun UserFormValue.toEntity() =
    UserFormValueJpaEntity(
        id = id,
        authenticationSectionId = authenticationSectionId,
        value = value,
        score = score,
        sectionType = sectionType,
        targetId = targetId
    )