package team.msg.sms.domain.file.model

import java.util.UUID

class File(
    val id: UUID,
    val fileName: String,
    val fileUrl: String,
    val fileType: FileType,
    val targetId: UUID
)