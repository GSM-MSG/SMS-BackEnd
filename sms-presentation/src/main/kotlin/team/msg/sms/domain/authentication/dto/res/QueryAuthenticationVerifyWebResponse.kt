package team.msg.sms.domain.authentication.dto.res

import team.msg.sms.domain.authentication.model.MarkingBoardType

data class QueryAuthenticationVerifyWebResponse(
    val name: String,
    val score: Double,
    val grader: String?,
    val markingBoardType: MarkingBoardType
)
