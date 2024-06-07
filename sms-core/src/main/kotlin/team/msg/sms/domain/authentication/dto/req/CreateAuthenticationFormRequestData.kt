package team.msg.sms.domain.authentication.dto.req

data class CreateAuthenticationFormRequestData(
    val title: String,
    val version: String,
    val formData: List<CreateAuthenticationAreaFormRequestData>
)
