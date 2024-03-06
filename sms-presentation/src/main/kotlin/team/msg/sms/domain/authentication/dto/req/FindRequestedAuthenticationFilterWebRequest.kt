package team.msg.sms.domain.authentication.dto.req

import org.springframework.web.bind.annotation.RequestParam
import team.msg.sms.domain.student.model.Department

data class FindRequestedAuthenticationFilterWebRequest(
    @RequestParam("grade", required = false) val grade: List<Int>?,
    @RequestParam("classNum", required = false) val classNum: List<Int>?,
    @RequestParam("department", required = false) val department: List<Department>?,
    @RequestParam("stuNumSort", required = false) val stuNumSort: String?,
    @RequestParam("requestTimeSort", required = false) val requestTimeSort: String?
) {
    fun toData() = FindRequestedAuthenticationFilterRequestData(
        grade = grade,
        classNum = classNum,
        department = department,
        stuNumSort = stuNumSort,
        requestTimeSort = requestTimeSort
    )
}
