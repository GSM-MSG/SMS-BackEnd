package team.msg.sms.domain.student.exception.error

import team.msg.sms.common.error.ErrorProperty
import team.msg.sms.common.error.ErrorStatus

enum class StudentErrorCode(
    private val status: Int,
    private val message: String
) : ErrorProperty {

    STUDENT_NOT_FOUND(ErrorStatus.NOT_FOUND, "학생이 존재하지 않습니다."),
    STU_NUM_NOR_RIGHT(ErrorStatus.BAD_REQUEST, "올바르지 않은 학번입니다."),
    STUDENT_ALREADY(ErrorStatus.CONFLICT, "학생 정보가 존재하는 유저입니다"),
    STUDENT_LINK_NOT_FOUND(ErrorStatus.CONFLICT, "학생 열람 정보가 존재하지 않습니다."),
    PORTFOLIO_INVALID_EXTENSION(ErrorStatus.CONFLICT, "포트폴리오의 확장자가 올바르지 않습니다."),
    ;

    override fun status(): Int = status
    override fun message(): String = message
}