package team.msg.sms.domain.authentication.exception.error

import team.msg.sms.common.error.ErrorProperty
import team.msg.sms.common.error.ErrorStatus

enum class AuthenticationErrorCode(
    private val status: Int,
    private val message: String
) : ErrorProperty {
    AUTHENTICATION_NOT_FOUND(ErrorStatus.NOT_FOUND, "인증제 활동을 찾을 수 없습니다."),
    UNSUITABLE_ACTIVITY_STATUS(ErrorStatus.CONFLICT, "요청을 처리하기에 적합하지 않은 인증제 활동 상태입니다."),
    ALREADY_AWARDED_SCORE(ErrorStatus.CONFLICT, "활동에 이미 점수가 부여된 상태입니다."),
    PERMISSION_ROLE_DENIED(ErrorStatus.FORBIDDEN, "학생이거나 교장, 교감, 부장, 담임 선생님만 조회할 수 있습니다."),
    ONLY_ACCESS_MYSELF(ErrorStatus.FORBIDDEN, "자기 자신만 조회할 수 있습니다."),
    INVALID_GRADE_CLASS(ErrorStatus.FORBIDDEN, "담임 선생님만 조회할 수 있습니다."),
    ALREADY_GIVEN_SCORE(ErrorStatus.CONFLICT, "이미 활동에 점수가 부여되었습니다."),
    NO_REQUESTED_ACTIVITY(ErrorStatus.CONFLICT, "활동이 아직 요청되지 않았습니다."),
    MARKING_BOARD_NOT_FOUND(ErrorStatus.NOT_FOUND, "학생이 제출한 폼이 조회되지 않습니다."),
    USER_FORM_VALUE_NOT_FOUND(ErrorStatus.NOT_FOUND, "채점 데이터의 존재하지 않는 폼 제출 데이터가 포함되어있습니다.")
    ;

    override fun status(): Int = status

    override fun message(): String = message

}