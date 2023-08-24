package team.msg.sms.domain.file.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.file.model.Image
import team.msg.sms.domain.file.service.CheckImageService

@Service
class CheckImageServiceImpl(

) : CheckImageService {
    override fun checkAddedImage(images: List<Image>, modifyImages: List<String>): List<String> =
        modifyImages
            .filterNot { images.map { it -> it.imageUrl }.contains(it) }

    override fun checkRemovedImage(images: List<Image>, modifyImages: List<String>): List<Image> =
        images
            .filterNot { modifyImages.contains(it.imageUrl) }
}