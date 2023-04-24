package team.msg.sms.domain.auth.exception.error

import team.msg.sms.common.error.ErrorProperty
import team.msg.sms.common.error.ErrorStatus

enum class AuthErrorCode(
    private val status: Int,
    private val message: String
) : ErrorProperty {

    REFRESH_NOT_FOUND(ErrorStatus.NOT_FOUND, "리프레쉬 토큰을 찾을 수 없습니다.")
    ;

    override fun status(): Int = status

    override fun message(): String = message
}