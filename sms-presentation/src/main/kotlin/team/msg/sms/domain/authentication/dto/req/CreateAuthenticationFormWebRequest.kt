package team.msg.sms.domain.authentication.dto.req

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CreateAuthenticationFormWebRequest(
    @field:NotNull
    val title: String,
    @field:NotNull
    @field:NotBlank
    val version: String,
    val formData: List<CreateAuthenticationAreaFormRequestData>
) {
    fun toData() =
        CreateAuthenticationFormRequestData(
            title = title,
            version = version,
            formData = formData
        )

}
