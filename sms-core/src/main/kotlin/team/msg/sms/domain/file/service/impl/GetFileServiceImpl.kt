package team.msg.sms.domain.file.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.file.model.File
import team.msg.sms.domain.file.service.GetFileService
import team.msg.sms.domain.file.spi.FilePort
import java.util.*

@Service
class GetFileServiceImpl(
    private val filePort: FilePort
) : GetFileService {
    override fun getFileByTargetUuidAndTypeEqualsAuthentication(targetId: UUID): List<File> =
        filePort.queryFileByTargetUuidsAndTypeEqualsAuthentication(targetId)
}