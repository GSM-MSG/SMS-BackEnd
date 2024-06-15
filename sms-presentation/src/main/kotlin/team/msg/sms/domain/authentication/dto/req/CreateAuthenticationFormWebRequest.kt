package team.msg.sms.domain.authentication.dto.req

import team.msg.sms.domain.file.dto.req.CreateFileRequestData
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CreateAuthenticationFormWebRequest(
    @field:NotNull
    val title: String,
    val files: List<CreateFileRequestData>,
    @field:NotNull
    @field:NotBlank
    val version: String,
    val formData: List<CreateAuthenticationAreaFormRequestData>
) {
    fun toData() =
        CreateAuthenticationFormRequestData(
            title = title,
            files = files,
            version = version,
            formData = formData
        )

}
