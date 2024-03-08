package team.msg.sms.domain.teacher.exception.error

import team.msg.sms.common.error.ErrorProperty
import team.msg.sms.common.error.ErrorStatus

enum class TeacherErrorCode(
    private val status: Int,
    private val message: String
) : ErrorProperty {

    TEACHER_ALREADY(ErrorStatus.CONFLICT, "선생님 정보가 존재하는 유저입니다."),
    HOMEROOM_TEACHER_ALREADY(ErrorStatus.CONFLICT, "동일 정보의 담임선생님이 존재합니다."),
    HOMEROOM_TEACHER_NOT_FOUNT(ErrorStatus.NOT_FOUND, "담임 선생님이 존재하지 않습니다.")
    ;

    override fun status(): Int = status
    override fun message(): String = message
}