package team.msg.sms.persistence.authentication.repository.queryDSL

import team.msg.sms.domain.authentication.dto.res.UserBoardPageResponseData
import team.msg.sms.domain.authentication.model.MarkingBoardType
import team.msg.sms.persistence.authentication.entity.MarkingBoardJpaEntity
import java.util.*

interface MarkingBoardCustomRepository {
    fun findMarkingBoardWithStudentId(studentId: UUID): MarkingBoardJpaEntity?

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