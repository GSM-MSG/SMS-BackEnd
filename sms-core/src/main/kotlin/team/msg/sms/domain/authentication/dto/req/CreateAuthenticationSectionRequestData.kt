package team.msg.sms.domain.authentication.dto.req

import team.msg.sms.domain.authentication.model.SectionType

data class CreateAuthenticationSectionRequestData(
    val sectionName: String,
    val description: String,
    val sectionScore: Int,
    val placeHolder: String?,
    val maxCount: Int,
    val sectionType: SectionType,
    val selectorSectionName: List<String>
)