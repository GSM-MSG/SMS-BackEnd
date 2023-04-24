package team.msg.sms.domain.user.exception.error

import team.msg.sms.common.error.ErrorProperty
import team.msg.sms.common.error.ErrorStatus

enum class UserErrorCode(
    private val status: Int,
    private val message: String,
) : ErrorProperty {

    USER_NOT_FOUND(ErrorStatus.NOT_FOUND, "유저가 존재하지 않습니다."),
    ROLE_NOT_EXISTS(ErrorStatus.NOT_FOUND, "권한이 존재하지 않습니다."),
    INTERNAL_SERVER_ERROR(ErrorStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
    ;

    override fun status(): Int = status

    override fun message(): String = message
}
