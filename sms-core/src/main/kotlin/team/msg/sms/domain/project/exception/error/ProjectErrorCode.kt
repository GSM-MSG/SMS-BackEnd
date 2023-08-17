package team.msg.sms.domain.project.exception.error

import team.msg.sms.common.error.ErrorProperty
import team.msg.sms.common.error.ErrorStatus

enum class ProjectErrorCode(
    private val status: Int,
    private val message: String
) : ErrorProperty {

    PROJECT_NOT_FOUND(ErrorStatus.NOT_FOUND, "프로젝트가 존재하지 않습니다."),
    ;

    override fun status(): Int = status
    override fun message(): String = message
}