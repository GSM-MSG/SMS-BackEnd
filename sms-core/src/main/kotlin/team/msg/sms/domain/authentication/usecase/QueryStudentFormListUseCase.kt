package team.msg.sms.domain.authentication.usecase

import team.msg.sms.common.annotation.ReadOnlyUseCase
import team.msg.sms.domain.authentication.dto.res.UserBoardPageResponseData
import team.msg.sms.domain.authentication.model.MarkingBoardType
import team.msg.sms.domain.authentication.service.AuthenticationFormService
import team.msg.sms.domain.authentication.service.MarkingBoardService
import team.msg.sms.domain.student.service.StudentService

@ReadOnlyUseCase
class QueryStudentFormListUseCase(
    private val studentService: StudentService,
    private val markingBoardService: MarkingBoardService,
    private val authenticationFormService: AuthenticationFormService
) {
    fun execute(page: Int, size: Int, type: List<MarkingBoardType>?): UserBoardPageResponseData {
        val studentIds = studentService.getStudentIds()

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