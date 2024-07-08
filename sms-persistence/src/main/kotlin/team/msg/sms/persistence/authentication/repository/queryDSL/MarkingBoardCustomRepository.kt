package team.msg.sms.persistence.authentication.repository.queryDSL

import team.msg.sms.domain.authentication.dto.res.UserBoardPageResponseData
import team.msg.sms.domain.authentication.model.MarkingBoardType
import java.util.*

interface MarkingBoardCustomRepository {
    fun findMarkingBoardWithStudentInfoByStudentIds(
        studentIds: List<UUID>,
        authenticationId: UUID,
        page: Int,
        size: Int
    ): UserBoardPageResponseData

    fun findMarkingBoardWithStudentInfoByStudentIdsWithType(
        studentIds: List<UUID>,
        authenticationId: UUID,
        page: Int,
        size: Int,
        type: List<MarkingBoardType>
    ): UserBoardPageResponseData

}