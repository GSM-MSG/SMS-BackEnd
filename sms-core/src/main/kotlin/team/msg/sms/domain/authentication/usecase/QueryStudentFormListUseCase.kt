package team.msg.sms.domain.authentication.usecase

import team.msg.sms.common.annotation.ReadOnlyUseCase
import team.msg.sms.domain.authentication.dto.res.UserBoardPageResponseData
import team.msg.sms.domain.authentication.model.MarkingBoardType
import team.msg.sms.domain.authentication.service.AuthenticationFormService
import team.msg.sms.domain.authentication.service.MarkingBoardService
import team.msg.sms.domain.student.service.StudentService
import team.msg.sms.domain.teacher.service.HomeroomTeacherService
import team.msg.sms.domain.teacher.service.TeacherService
import team.msg.sms.domain.user.service.UserService

@ReadOnlyUseCase
class QueryStudentFormListUseCase(
    private val studentService: StudentService,
    private val userService: UserService,
    private val markingBoardService: MarkingBoardService,
    private val authenticationFormService: AuthenticationFormService,
    private val homeroomTeacherService: HomeroomTeacherService
) {
    fun execute(page: Int, size: Int, type: List<MarkingBoardType>?): UserBoardPageResponseData {
        val currentUser = userService.getCurrentUser()
        val currentHomeroomTeacher = homeroomTeacherService.getHomeroomTeacherByUserId(currentUser.id)

        val grade = currentHomeroomTeacher.grade
        val classNum = currentHomeroomTeacher.classNum
        val studentIds = studentService.getStudentIdsByGradeAndClassNum(grade, classNum)

        val authenticationFormId = authenticationFormService.getActiveAuthenticationFormId()

        return if (type.isNullOrEmpty()) {
            markingBoardService.getMarkingBoardByStudentIds(
                studentIds = studentIds,
                authenticationId = authenticationFormId,
                page = page,
                size = size
            )
        } else {
            markingBoardService.getMarkingBoardByStudentIdsWithType(
                studentIds = studentIds,
                authenticationId = authenticationFormId,
                page = page,
                size = size,
                type = type
            )
        }
    }
}