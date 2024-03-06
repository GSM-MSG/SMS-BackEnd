package team.msg.sms.domain.authentication.dto.res

import team.msg.sms.domain.student.model.Department
import java.time.LocalDate
import java.util.*

data class QueryRequestedAuthenticaionResponseData (
    val id: UUID,
    val requestTime: LocalDate,
    val stuNum: String,
    val department: Department,
    val title: String
)