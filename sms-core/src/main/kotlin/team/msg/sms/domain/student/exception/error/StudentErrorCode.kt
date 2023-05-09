package team.msg.sms.domain.student.exception.error

import team.msg.sms.common.error.ErrorProperty
import team.msg.sms.common.error.ErrorStatus

enum class StudentErrorCode(
    private val status: Int,
    private val message: String
) : ErrorProperty {

    STUDENT_NOT_FOUND(ErrorStatus.NOT_FOUND, "학생이 존재하지 않습니다."),
    STU_NUM_NOR_RIGHT(ErrorStatus.BAD_REQUEST, "올바르지 않은 학번입니다.")
    ;

    override fun status(): Int = status
    override fun message(): String = message
}