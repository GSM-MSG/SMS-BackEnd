package team.msg.sms.persistence.authentication

import org.springframework.stereotype.Component
import team.msg.sms.domain.authentication.dto.res.UserBoardPageResponseData
import team.msg.sms.domain.authentication.dto.res.UserBoardWithStudentInfoResponseData
import team.msg.sms.domain.authentication.model.MarkingBoard
import team.msg.sms.domain.authentication.model.MarkingBoardType
import team.msg.sms.domain.authentication.spi.MarkingBoardPort
import team.msg.sms.persistence.authentication.mapper.toEntity
import team.msg.sms.persistence.authentication.repository.MarkingBoardJpaRepository
import team.msg.sms.persistence.authentication.repository.queryDSL.MarkingBoardCustomRepository
import java.util.*

@Component
class MarkingBoardPersistenceAdapter(
    private val markingBoardJpaRepository: MarkingBoardJpaRepository,
    private val markingBoardCustomRepository: MarkingBoardCustomRepository
) : MarkingBoardPort {
    override fun save(markingBoard: MarkingBoard) {
        markingBoardJpaRepository.save(markingBoard.toEntity())
    }

    override fun queryMarkingBoardByStudentIds(
        studentIds: List<UUID>,
        authenticationId: UUID,
        page: Int,
        size: Int
    ): UserBoardPageResponseData {
        return markingBoardCustomRepository.findMarkingBoardWithStudentInfoByStudentIds(
            studentIds,
            authenticationId,
            page,
            size
        )
    }

    override fun queryMarkingBoardByStudentIdsWithType(
        studentIds: List<UUID>,
        authenticationId: UUID,
        page: Int,
        size: Int,
        type: List<MarkingBoardType>
    ): UserBoardPageResponseData {
        return markingBoardCustomRepository.findMarkingBoardWithStudentInfoByStudentIdsWithType(
            studentIds,
            authenticationId,
            page,
            size,
            type
        )
    }

}