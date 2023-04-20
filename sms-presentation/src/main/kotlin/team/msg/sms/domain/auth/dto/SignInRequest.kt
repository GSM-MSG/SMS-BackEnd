package team.msg.sms.domain.auth.dto

import org.jetbrains.annotations.NotNull
import team.msg.sms.domain.auth.dto.request.SignInData

data class SignInRequest(
    @field:NotNull
    val code: String
) {
    fun toData() = SignInData(
        code = code
    )
}
