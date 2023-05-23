package team.msg.sms.domain.file.usecase

import team.msg.sms.common.annotation.Service
import team.msg.sms.common.util.FileUtil.isImageCorrectExtension
import team.msg.sms.domain.file.exception.ImageInvalidExtensionException
import team.msg.sms.domain.file.spi.UploadFilePort
import java.io.File

@Service
class UploadImageUseCase(
    private val uploadFilePort: UploadFilePort
) {
    fun execute(file: File): String {
        if (!file.extension.isImageCorrectExtension()) {
            file.delete()
            throw ImageInvalidExtensionException
        }
        return uploadFilePort.upload(file)
    }
}