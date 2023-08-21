package team.msg.sms.domain.student.dto.res

import team.msg.sms.domain.prize.dto.res.PrizeResponseData
import team.msg.sms.domain.project.dto.res.ProjectResponseData
import team.msg.sms.domain.student.model.Department

data class DetailStudentInfoResponseData(
    val name: String,
    val introduce: String,
    val grade: Int,
    val classNum: Int,
    val number: Int,
    val department: Department,
    val major: String,
    val profileImg: String,
    val techStacks: List<String>,
    val projects: List<ProjectResponseData>,
    val prizes: List<PrizeResponseData>
)