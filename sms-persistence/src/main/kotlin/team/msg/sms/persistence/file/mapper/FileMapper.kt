package team.msg.sms.persistence.file.mapper

import team.msg.sms.domain.file.model.File
import team.msg.sms.persistence.file.entity.FileJpaEntity

fun FileJpaEntity.toDomain() = File(
    id = id,
    fileName = fileName,
    fileUrl = fileUrl,
    fileType = fileType,
    targetId = targetId
)

fun File.toEntity() = FileJpaEntity(
    id = id,
    fileName = fileName,
    fileUrl = fileUrl,
    fileType = fileType,
    targetId = targetId
)