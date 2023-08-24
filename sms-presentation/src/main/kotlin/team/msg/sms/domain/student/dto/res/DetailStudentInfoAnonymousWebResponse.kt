package team.msg.sms.domain.student.dto.res

import team.msg.sms.domain.prize.dto.res.PrizeResponseData
import team.msg.sms.domain.project.dto.res.ProjectResponseData

data class DetailStudentInfoAnonymousWebResponse(
    val name: String,
    val introduce: String,
    val major: String,
    val profileImg: String,
    val techStacks: List<String>,
    val projects: List<ProjectResponseData>,
    val prizes: List<PrizeResponseData>
)