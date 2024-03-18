package team.msg.sms.domain.authentication.dto.req

import javax.validation.constraints.NotBlank

data class ApproveAuthenticationWebRequest (
    @field:NotBlank
    val score: Int,

    @field:NotBlank
    val reason: String,
) {
    fun toData() = ApproveAuthenticationRequestData(
        score = score,
        reason = reason
    )
}