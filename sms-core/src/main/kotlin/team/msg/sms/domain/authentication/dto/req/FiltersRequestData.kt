package team.msg.sms.domain.authentication.dto.req

import team.msg.sms.domain.student.model.Department

data class FiltersRequestData(
    val grade: List<Int>?,
    val classNum: List<Int>?,
    val department: List<Department>?,
    val stuNumSort: String?,
    val requestTimeSort: String?
)
