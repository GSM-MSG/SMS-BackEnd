package team.msg.sms.domain.authentication.dto.res

import team.msg.sms.domain.student.model.Department
import java.time.LocalDate
import java.util.*

data class RequestedAuthenticationResponseData (
    val id: UUID,
    val requestedTime: LocalDate,
    val stuNum: String,
    val name: String,
    val department: Department,
    val title: String
)