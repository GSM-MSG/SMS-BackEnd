package team.msg.sms.domain.teacher.exception.error

import team.msg.sms.common.error.ErrorProperty
import team.msg.sms.common.error.ErrorStatus

enum class TeacherErrorCode(
    private val status: Int,
    private val message: String
) : ErrorProperty {

    TEACHER_ALREADY(ErrorStatus.CONFLICT, "선생님 정보가 존재하는 유저입니다"),
    ;

    override fun status(): Int = status
    override fun message(): String = message
}