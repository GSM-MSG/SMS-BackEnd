package team.msg.sms.domain.authentication.model

import java.util.*

data class MarkingBoard(
    val id: UUID,
    val title: String,
    val authenticationId: UUID,
    val studentId: UUID,
    val totalScore: Double,
    val markingBoardType: MarkingBoardType
)
