package team.msg.sms.domain.file

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import team.msg.sms.common.extension.toFile
import team.msg.sms.domain.file.dto.res.UploadFileResponse
import team.msg.sms.domain.file.dto.res.UploadImageResponse
import team.msg.sms.domain.file.usecase.UploadFileUseCase
import team.msg.sms.domain.file.usecase.UploadImageUseCase

@RestController
@RequestMapping("/file")
class FileWebAdapter(
    private val uploadFileUseCase: UploadFileUseCase,
    private val uploadImageUseCase: UploadImageUseCase
) {
    @PostMapping
    fun uploadFile(@RequestPart(name = "file") file: MultipartFile?): UploadFileResponse {
        val result = uploadFileUseCase.execute(file = file!!.toFile())
        return UploadFileResponse(result)
    }

    @PostMapping("/image")
    fun uploadImage(@RequestPart(name = "file") file: MultipartFile?): UploadImageResponse {
        val result = uploadImageUseCase.execute(file = file!!.toFile())
        return UploadImageResponse(result)
    }
}