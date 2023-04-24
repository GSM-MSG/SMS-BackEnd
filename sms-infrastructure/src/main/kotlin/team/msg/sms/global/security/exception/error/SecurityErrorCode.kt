package team.msg.sms.global.security.exception.error

import team.msg.sms.common.error.ErrorProperty
import team.msg.sms.common.error.ErrorStatus

enum class SecurityErrorCode(
    private val status: Int,
    private val message: String
) : ErrorProperty {

    INVALID_TOKEN(ErrorStatus.UNAUTHORIZED,"유효하지 않은 토큰입니다."),
    EXPIRED_TOKEN(ErrorStatus.UNAUTHORIZED, "만료된 토큰 입니다."),
    UNEXPECTED_TOKEN(ErrorStatus.UNAUTHORIZED, "지원되지 않는 형식이나 구성의 JWT 토큰이 아닙니다."),
    INVALID_ROLE(ErrorStatus.UNAUTHORIZED, "유효하지 않은 권한 입니다.")
    ;

    override fun status(): Int = status

    override fun message(): String = message
}
