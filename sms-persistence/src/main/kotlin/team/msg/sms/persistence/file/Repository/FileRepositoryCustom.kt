package team.msg.sms.persistence.file.Repository

import org.springframework.data.repository.query.Param
import team.msg.sms.persistence.file.entity.FileJpaEntity
import java.util.*

interface FileRepositoryCustom {
    fun findAllByTargetIdsAndTypeEqualsAuthentication(targetIds: List<UUID>): List<FileJpaEntity>
}