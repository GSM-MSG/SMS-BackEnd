package team.msg.sms.persistence.authentication.mapper

import team.msg.sms.domain.authentication.model.UserFormValue
import team.msg.sms.persistence.authentication.entity.UserFormValueJpaEntity

fun UserFormValueJpaEntity.toDomain() =
    UserFormValue(
        id = id,
        value = value,
        score = score,
        fieldType = fieldType,
        targetId = targetId,
        createdBy = createdBy,
        createdAt = createdAt,
        setId = setId,
        authenticationFormId = authenticationFormId,
        authenticationSectionId = authenticationSectionId,
        authenticationFieldId = authenticationFieldId,
        authenticationFieldGroupId = authenticationFieldGroupId
    )

fun UserFormValue.toEntity() =
    UserFormValueJpaEntity(
        id = id,
        value = value,
        score = score,
        fieldType = fieldType,
        targetId = targetId,
        createdBy = createdBy,
        createdAt = createdAt,
        setId = setId,
        authenticationFormId = authenticationFormId,
        authenticationSectionId = authenticationSectionId,
        authenticationFieldId = authenticationFieldId,
        authenticationFieldGroupId = authenticationFieldGroupId
    )