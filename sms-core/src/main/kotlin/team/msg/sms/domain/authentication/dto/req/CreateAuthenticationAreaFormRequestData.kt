package team.msg.sms.domain.authentication.dto.req

data class CreateAuthenticationAreaFormRequestData(
    val title: String,
    val data: List<CreateAuthenticationSectionRequestData>
)