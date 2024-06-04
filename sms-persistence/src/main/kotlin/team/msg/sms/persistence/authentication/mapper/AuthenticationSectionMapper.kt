package team.msg.sms.persistence.authentication.mapper

import team.msg.sms.domain.authentication.model.AuthenticationSection
import team.msg.sms.persistence.authentication.entity.AuthenticationSectionJpaEntity

fun AuthenticationSection.toEntity() = AuthenticationSectionJpaEntity(
    id = id,
    groupId = groupId,
    sectionName = sectionName,
    description = description,
    placeHolder = placeHolder,
    sectionType = sectionType,
    sectionScore = sectionScore,
    maxCount = maxCount
)

fun AuthenticationSectionJpaEntity.toDomain() = AuthenticationSection(
    id = id,
    groupId = groupId,
    sectionName = sectionName,
    description = description,
    placeHolder = placeHolder,
    sectionType = sectionType,
    sectionScore = sectionScore,
    maxCount = maxCount
)