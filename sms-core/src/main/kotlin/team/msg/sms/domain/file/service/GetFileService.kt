package team.msg.sms.domain.file.service

import team.msg.sms.domain.file.model.File
import java.util.UUID

interface GetFileService {
    fun getFileByTargetUuidAndTypeEqualsAuthentication(targetId: UUID): List<File>
}