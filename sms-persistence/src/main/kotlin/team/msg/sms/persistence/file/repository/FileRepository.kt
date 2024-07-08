package team.msg.sms.persistence.file.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import team.msg.sms.domain.file.model.FileType
import team.msg.sms.persistence.file.entity.FileJpaEntity
import java.util.UUID

@Repository
interface FileRepository : CrudRepository<FileJpaEntity, UUID> {
    fun findAllByTargetIdAndAndFileType(targetId: UUID, fileType: FileType): List<FileJpaEntity>
}