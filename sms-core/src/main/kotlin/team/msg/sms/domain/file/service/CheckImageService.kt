package team.msg.sms.domain.file.service

import team.msg.sms.domain.file.model.Image

interface CheckImageService {
    fun checkAddedImage(images: List<Image>, modifyImages: List<String>): List<String>
    fun checkRemovedImage(images: List<Image>, modifyImages: List<String>): List<Image>
}