package team.msg.sms.domain.auth.dto.req

import org.jetbrains.annotations.NotNull

data class SignInWebRequest(
    @field:NotNull
    val code: String
) {
    fun toData() = SignInRequestData(
        code = code
    )
}
