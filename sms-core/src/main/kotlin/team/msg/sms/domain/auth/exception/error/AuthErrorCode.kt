package team.msg.sms.domain.auth.exception.error

import team.msg.sms.common.error.ErrorProperty
import team.msg.sms.common.error.ErrorStatus

enum class AuthErrorCode(
    private val status: Int,
    private val message: String
) : ErrorProperty {

    REFRESH_NOT_FOUND(ErrorStatus.NOT_FOUND, "리프레쉬 토큰을 찾을 수 없습니다."),
    EXPIRED_CODE(ErrorStatus.UNAUTHORIZED, "만료된 코드입니다"),
    SECRET_MISMATCH(ErrorStatus.BAD_REQUEST, "클라이언트 시크릿 값이 일치하지 않습니다."),
    SERVICE_NOT_FOUND(ErrorStatus.NOT_FOUND, "서비스를 찾지 못했습니다."),
    ;

    override fun status(): Int = status

    override fun message(): String = message
}