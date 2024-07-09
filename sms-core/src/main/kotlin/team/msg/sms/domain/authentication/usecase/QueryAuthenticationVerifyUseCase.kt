package team.msg.sms.domain.authentication.usecase

import team.msg.sms.common.annotation.ReadOnlyUseCase
import team.msg.sms.domain.authentication.dto.res.QueryAuthenticationVerifyResponseData
import team.msg.sms.domain.authentication.model.MarkingBoardType
import team.msg.sms.domain.authentication.service.MarkingBoardService
import team.msg.sms.domain.student.service.StudentService

@ReadOnlyUseCase
class QueryAuthenticationVerifyUseCase(
    private val studentService: StudentService,
    private val markingBoardService: MarkingBoardService
) {
    fun execute(): QueryAuthenticationVerifyResponseData {
        val student = studentService.currentStudent()
        val markingBoard = markingBoardService.verifyMarkingBoardByStudentId(student.id)

        return if (markingBoard == null) {
            QueryAuthenticationVerifyResponseData(
                name = student.name,
                score = 0.0,
                grader = null,
                markingBoardType = MarkingBoardType.NOT_SUBMITTED
            )
        } else {
            QueryAuthenticationVerifyResponseData(
                name = student.name,
                score = markingBoard.totalScore,
                grader = markingBoard.graderName,
                markingBoardType = markingBoard.markingBoardType
            )
        }

    }
}