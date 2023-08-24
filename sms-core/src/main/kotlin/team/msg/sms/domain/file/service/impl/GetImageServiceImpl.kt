package team.msg.sms.domain.file.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.file.model.Image
import team.msg.sms.domain.file.service.GetImageService
import team.msg.sms.domain.file.spi.ImagePort

@Service
class GetImageServiceImpl(
    private val imagePort: ImagePort
) : GetImageService {
    override fun getAllByProjectId(projectId: Long): List<Image> =
        imagePort.queryAllByProjectId(projectId)

    override fun getByImageUrlAndProjectId(imageUrl: String, projectId: Long): Image? =
        imagePort.queryByImageUrlAndProjectId(imageUrl, projectId)
}