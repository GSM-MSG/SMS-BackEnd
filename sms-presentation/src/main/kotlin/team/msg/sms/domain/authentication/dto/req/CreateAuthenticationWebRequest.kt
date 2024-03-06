package team.msg.sms.domain.authentication.dto.req

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class CreateAuthenticationWebRequest (
    @field:NotBlank
    val title: String,

    @field:NotBlank
    val content: String,

    @field:NotNull
    @field:Size(max = 4)
    val activityImages: List<String>
) {
    fun toData() = CreateAuthenticationRequestData(
        title = title,
        content = content,
        activityImages = activityImages
    )
}