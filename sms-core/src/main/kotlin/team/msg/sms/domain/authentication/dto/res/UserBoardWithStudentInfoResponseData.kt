package team.msg.sms.domain.authentication.dto.res

import team.msg.sms.domain.authentication.model.MarkingBoardType
import java.util.*

data class UserBoardWithStudentInfoResponseData(
    val id: UUID?,
    val title: String,
    val studentId: UUID,
    val type: MarkingBoardType,
    val profileImgUrl: String,
    val totalScore: Double
)