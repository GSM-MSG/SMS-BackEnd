package team.msg.sms.domain.authentication.spi

import team.msg.sms.domain.authentication.dto.res.UserBoardPageResponseData
import team.msg.sms.domain.authentication.model.MarkingBoard
import team.msg.sms.domain.authentication.model.MarkingBoardType
import java.util.UUID

interface QueryMarkingBoardPort {
    fun verifyMarkingBoardByStudentId(studentId: UUID): MarkingBoard?
    fun queryMarkingBoardById(id: UUID): MarkingBoard?
    fun queryMarkingBoardByStudentIds(
        studentIds: List<UUID>,
        authenticationId: UUID,
        page: Int,
        size: Int
    ): UserBoardPageResponseData

    fun queryMarkingBoardByStudentIdsWithType(
        studentIds: List<UUID>,
        authenticationId: UUID,
        page: Int,
        size: Int,
        type: List<MarkingBoardType>
    ): UserBoardPageResponseData
}