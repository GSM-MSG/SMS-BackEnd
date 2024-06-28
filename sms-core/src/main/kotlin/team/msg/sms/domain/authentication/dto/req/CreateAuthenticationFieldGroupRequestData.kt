package team.msg.sms.domain.authentication.dto.req

data class CreateAuthenticationFieldGroupRequestData(
    val maxScore: Double,
    val fieldData: List<CreateAuthenticationFieldRequestData>
)