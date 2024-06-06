package team.msg.sms.domain.file.spi

import team.msg.sms.domain.file.model.File
import java.util.UUID

interface QueryFilePort {
    fun queryFileByTargetUuidsAndTypeEqualsAuthentication(targetIds: List<UUID>): List<File>
}