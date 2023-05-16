package team.msg.sms.domain.file.usecase

import team.msg.sms.common.annotation.Service
import team.msg.sms.common.util.FileUtil
import team.msg.sms.domain.file.exception.FileInvalidExtensionException
import team.msg.sms.domain.file.spi.UploadFilePort
import java.io.File

@Service
class UploadFileUseCase(
    private val uploadFilePort: UploadFilePort
) {
    fun execute(file: File): String {
        if (!FileUtil.isHWPCorrectExtension(file.extension)) {
            file.delete()
            throw FileInvalidExtensionException
        }
        return uploadFilePort.upload(file)
    }
}