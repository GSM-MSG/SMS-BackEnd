package team.msg.sms.domain.authentication.dto.req

import team.msg.sms.domain.authentication.model.SectionType

data class CreateAuthenticationSectionRequestData(
    val sectionName: String,
    val maxCount: Int,
    val fieldData: List<CreateAuthenticationFieldRequestData>
)