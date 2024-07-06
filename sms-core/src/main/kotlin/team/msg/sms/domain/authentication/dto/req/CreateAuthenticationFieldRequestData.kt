package team.msg.sms.domain.authentication.dto.req

import team.msg.sms.domain.authentication.model.FieldType

data class CreateAuthenticationFieldRequestData(
    val description: String?,
    val placeholder: String?,
    val fieldType: FieldType,
    val selectorSectionName: List<String>
)
