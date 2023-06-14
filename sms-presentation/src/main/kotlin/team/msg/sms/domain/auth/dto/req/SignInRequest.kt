package team.msg.sms.domain.auth.dto.req

import org.jetbrains.annotations.NotNull
import team.msg.sms.domain.auth.dto.SignInData

data class SignInRequest(
    @field:NotNull
    val code: String
) {
    fun toData() = SignInData(
        code = code
    )
}
