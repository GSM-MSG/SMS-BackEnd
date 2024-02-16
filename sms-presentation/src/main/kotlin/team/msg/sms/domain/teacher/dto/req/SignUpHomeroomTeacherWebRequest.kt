package team.msg.sms.domain.teacher.dto.req

import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

data class SignUpHomeroomTeacherWebRequest(
    @field:Min(1)
    @field:Max(3)
    @field:NotNull
    val grade: Int,

    @field:Min(1)
    @field:Max(4)
    @field:NotNull
    val classNum: Int
)
