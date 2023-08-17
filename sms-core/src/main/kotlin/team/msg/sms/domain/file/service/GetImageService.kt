package team.msg.sms.domain.file.service

import team.msg.sms.domain.file.model.Image

interface GetImageService {
    fun getAllByProjectId(projectId: Long): List<Image>
}