package team.msg.sms.domain.file

import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import team.msg.sms.common.extension.toFile
import team.msg.sms.domain.file.dto.res.UploadFileWebResponse
import team.msg.sms.domain.file.dto.res.UploadImageWebResponse
import team.msg.sms.domain.file.usecase.UploadFileUseCase
import team.msg.sms.domain.file.usecase.UploadImageUseCase

@RestController
@RequestMapping("/file")
class FileWebAdapter(
    private val uploadFileUseCase: UploadFileUseCase,
    private val uploadImageUseCase: UploadImageUseCase
) {
    @PostMapping
    fun uploadFile(@RequestPart(name = "file") file: MultipartFile): UploadFileWebResponse {
        val result = uploadFileUseCase.execute(file = file.toFile())
        return UploadFileWebResponse(result)
    }

    @PostMapping("/image")
    fun uploadImage(@RequestPart(name = "file") file: MultipartFile): UploadImageWebResponse {
        val result = uploadImageUseCase.execute(file = file.toFile())
        return UploadImageWebResponse(result)
    }
}