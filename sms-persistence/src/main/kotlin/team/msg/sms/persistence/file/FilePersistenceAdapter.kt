package team.msg.sms.persistence.file

import org.springframework.stereotype.Component
import team.msg.sms.domain.file.model.File
import team.msg.sms.domain.file.spi.FilePort
import team.msg.sms.persistence.file.Repository.FileRepository
import team.msg.sms.persistence.file.Repository.FileRepositoryCustom
import team.msg.sms.persistence.file.mapper.toDomain
import java.util.*

@Component
class FilePersistenceAdapter(
    private val fileRepository: FileRepository,
    private val fileRepositoryCustom: FileRepositoryCustom
) : FilePort {
    override fun queryFileByTargetUuidsAndTypeEqualsAuthentication(targetIds: List<UUID>): List<File> =
        fileRepositoryCustom.findAllByTargetIdsAndTypeEqualsAuthentication(targetIds).map { it.toDomain() }

}