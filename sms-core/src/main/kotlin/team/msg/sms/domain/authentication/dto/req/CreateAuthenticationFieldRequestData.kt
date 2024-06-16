package team.msg.sms.domain.authentication.dto.req

import team.msg.sms.domain.authentication.model.FieldType

data class CreateAuthenticationFieldRequestData(
    val description: String?,
    val placeHolder: String?,
    val fieldInputType: FieldType,
    val selectorSectionName: List<String>
)
