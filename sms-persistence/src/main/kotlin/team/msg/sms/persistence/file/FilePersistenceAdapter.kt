package team.msg.sms.persistence.file

import org.springframework.stereotype.Component
import team.msg.sms.domain.file.model.File
import team.msg.sms.domain.file.model.FileType
import team.msg.sms.domain.file.spi.FilePort
import team.msg.sms.persistence.file.Repository.FileRepository
import team.msg.sms.persistence.file.Repository.FileRepositoryCustom
import team.msg.sms.persistence.file.mapper.toDomain
import team.msg.sms.persistence.file.mapper.toEntity
import java.util.*

@Component
class FilePersistenceAdapter(
    private val fileRepository: FileRepository,
    private val fileRepositoryCustom: FileRepositoryCustom
) : FilePort {
    override fun queryFileByTargetUuidsAndTypeEqualsAuthentication(targetId: UUID): List<File> =
        fileRepository.findAllByTargetIdAndAndFileType(targetId, FileType.AUTHENTICATION).map { it.toDomain() }


    override fun saveAll(file: List<File>) {
        fileRepository.saveAll(file.map { it.toEntity() })
    }

}