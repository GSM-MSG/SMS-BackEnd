package team.msg.sms.domain.authentication.dto.req

data class CreateAuthenticationSectionRequestData(
    val sectionName: String,
    val maxCount: Int,
    val fieldData: List<CreateAuthenticationFieldRequestData>
)