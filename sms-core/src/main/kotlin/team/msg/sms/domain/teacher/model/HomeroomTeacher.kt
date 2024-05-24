package team.msg.sms.domain.teacher.model

import team.msg.sms.common.annotation.Aggregate
import java.util.*

@Aggregate
data class HomeroomTeacher (
    val id: Long = 0,
    val grade: Int,
    val classNum: Int,
    val teacherId: UUID,
)