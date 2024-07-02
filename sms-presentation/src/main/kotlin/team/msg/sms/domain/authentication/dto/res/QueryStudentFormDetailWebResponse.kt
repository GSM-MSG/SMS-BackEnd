package team.msg.sms.domain.authentication.dto.res

import java.util.UUID

data class QueryStudentFormDetailWebResponse(
    val markingBoardId: UUID,
    val title: String,
    val content: List<StudentAuthenticationFormResponseData.Area>
)
