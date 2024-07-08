package team.msg.sms.persistence.file.repository

import team.msg.sms.persistence.file.entity.FileJpaEntity
import java.util.*

interface FileRepositoryCustom {
    fun findAllByTargetIdsAndTypeEqualsAuthentication(targetIds: List<UUID>): List<FileJpaEntity>
}