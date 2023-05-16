package team.msg.sms.domain.file

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import team.msg.sms.common.extension.toFile
import team.msg.sms.domain.file.dto.res.UploadFileResponse
import team.msg.sms.domain.file.usecase.UploadFileUseCase

@RestController
@RequestMapping("/file")
class FileWebAdapter(
    private val uploadFileUseCase: UploadFileUseCase
) {
    @PostMapping
    fun uploadFile(@RequestPart(name = "file") file: MultipartFile?): UploadFileResponse {
        val result = uploadFileUseCase.execute(file = file!!.toFile())
        return UploadFileResponse(result)
    }
}