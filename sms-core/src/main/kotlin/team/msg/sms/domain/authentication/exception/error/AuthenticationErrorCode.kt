package team.msg.sms.domain.authentication.exception.error

import team.msg.sms.common.error.ErrorProperty
import team.msg.sms.common.error.ErrorStatus

enum class AuthenticationErrorCode(
    private val status: Int,
    private val message: String
) : ErrorProperty {
    AUTHENTICATION_NOT_FOUND(ErrorStatus.NOT_FOUND, "인증제 활동을 찾을 수 없습니다.")
    ;

    override fun status(): Int = status

    override fun message(): String = message

}