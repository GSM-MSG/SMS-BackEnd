package team.msg.sms.domain.authentication.exception.error

import team.msg.sms.common.error.ErrorProperty
import team.msg.sms.common.error.ErrorStatus

enum class AuthenticationErrorCode(
    private val status: Int,
    private val message: String
) : ErrorProperty {
    AUTHENTICATION_NOT_FOUND(ErrorStatus.NOT_FOUND, "인증제 활동을 찾을 수 없습니다."),
    UNSUITABLE_ACTIVITY_STATUS(ErrorStatus.CONFLICT, "요청을 처리하기에 적합하지 않은 인증제 활동 상태입니다.")
    ;

    override fun status(): Int = status

    override fun message(): String = message

}