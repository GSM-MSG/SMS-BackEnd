package team.msg.sms.domain.authentication.service

import team.msg.sms.domain.authentication.dto.res.UserBoardPageResponseData
import team.msg.sms.domain.authentication.model.MarkingBoardType
import java.util.*

interface GetMarkingBoardService {
    fun getMarkingBoardByStudentIds(
        studentIds: List<UUID>,
        authenticationId: UUID,
        page: Int,
        size: Int
    ): UserBoardPageResponseData

    fun getMarkingBoardByStudentIdsWithType(
        studentIds: List<UUID>,
        authenticationId: UUID,
        page: Int,
        size: Int, type: List<MarkingBoardType>
    ): UserBoardPageResponseData

}