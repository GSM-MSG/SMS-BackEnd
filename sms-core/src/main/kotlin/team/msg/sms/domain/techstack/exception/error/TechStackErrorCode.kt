package team.msg.sms.domain.techstack.exception.error

import team.msg.sms.common.error.ErrorProperty
import team.msg.sms.common.error.ErrorStatus

enum class TechStackErrorCode(
    private val status: Int,
    private val message: String
) : ErrorProperty {

    TECH_STACK_NOT_FOUND(ErrorStatus.NOT_FOUND, "기술스택이 존재하지 않습니다."),
    ;

    override fun status(): Int = status
    override fun message(): String = message
}