package team.msg.sms.domain.authentication.dto.req

import team.msg.sms.domain.authentication.model.SectionType
import java.util.*

data class CreateAuthenticationFieldRequestData(
    val description: String?,
    val placeHolder: String?,
    val fieldInputType: SectionType,
    val selectorSectionName: List<String>
)
