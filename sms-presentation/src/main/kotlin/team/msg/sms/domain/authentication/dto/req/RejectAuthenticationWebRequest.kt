package team.msg.sms.domain.authentication.dto.req

import javax.validation.constraints.NotBlank

data class RejectAuthenticationWebRequest (
    @field:NotBlank
    val score: Int,

    @field:NotBlank
    val reason: String,
) {
    fun toData() = RejectAuthenticationRequestData(
        score = score,
        reason = reason
    )
}