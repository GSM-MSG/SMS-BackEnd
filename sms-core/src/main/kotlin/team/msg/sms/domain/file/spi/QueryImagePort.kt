package team.msg.sms.domain.file.spi

import team.msg.sms.domain.file.model.Image

interface QueryImagePort {
    fun queryAllByProjectId(projectId: Long): List<Image>
}