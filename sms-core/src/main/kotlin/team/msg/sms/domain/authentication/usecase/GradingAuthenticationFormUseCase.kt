package team.msg.sms.domain.authentication.usecase

import team.msg.sms.common.annotation.UseCase
import team.msg.sms.domain.authentication.dto.req.GradingRequestData
import team.msg.sms.domain.authentication.exception.UserFormValueNotFoundException
import team.msg.sms.domain.authentication.model.MarkingBoardType
import team.msg.sms.domain.authentication.model.MarkingType
import team.msg.sms.domain.authentication.model.MarkingValue
import team.msg.sms.domain.authentication.service.MarkingBoardService
import team.msg.sms.domain.authentication.service.MarkingValueService
import team.msg.sms.domain.authentication.service.UserFormValueService
import team.msg.sms.domain.teacher.service.TeacherService
import team.msg.sms.domain.user.service.UserService
import java.time.LocalDateTime
import java.util.UUID

@UseCase
class GradingAuthenticationFormUseCase(
    private val markingValueService: MarkingValueService,
    private val markingBoardService: MarkingBoardService,
    private val userFormValueService: UserFormValueService,
    private val teacherService: TeacherService,
    private val userService: UserService
) {
    fun execute(markingBoardId: UUID, gradingDataList: List<GradingRequestData>) {
        //요청한 setId 들 중 실제 userFormValue 테이블에 없는 setId 일 경우 최종점수에 영향을 미칠 수 있어 예외처리
        if (!userFormValueService.checkUserFormValueBySetIds(gradingDataList.map { it.setId })) throw UserFormValueNotFoundException

        val teacher = teacherService.currentTeacher()

        val user = userService.getUserById(teacher.userId)

        val markingValueList = markingValueService.findMarkingValueListByMarkingBoardId(markingBoardId)

        val markingValueMap = markingValueList.associateBy { it.setId }

        val saveMarkingValueList = markingValueService.saveAll(
            gradingDataList.map { gradingData ->
                markingValueMap[gradingData.setId]?.copy(
                    score = gradingData.score,
                    createdAt = LocalDateTime.now(),
                    createdBy = teacher.id,
                    type = if (gradingData.score == null) MarkingType.REJECT else MarkingType.RESOLVE
                ) ?: MarkingValue(
                    id = UUID.randomUUID(),
                    score = gradingData.score,
                    createdAt = LocalDateTime.now(),
                    createdBy = teacher.id,
                    setId = gradingData.setId,
                    markingBoardId = markingBoardId,
                    type = if (gradingData.score == null) MarkingType.REJECT else MarkingType.RESOLVE
                )
            }
        )

        val totalScore = saveMarkingValueList.sumOf { it.score ?: 0.0 }

        markingBoardService.getMarkingBoardById(markingBoardId).let { markingBoard ->
            markingBoardService.save(
                markingBoard.copy(
                    totalScore = totalScore,
                    markingBoardType = MarkingBoardType.COMPLETED,
                    graderName = user.name
                )
            )
        }
    }
}