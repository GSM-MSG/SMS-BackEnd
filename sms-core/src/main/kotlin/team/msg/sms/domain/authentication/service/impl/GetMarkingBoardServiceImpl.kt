package team.msg.sms.domain.authentication.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.authentication.dto.res.UserBoardPageResponseData
import team.msg.sms.domain.authentication.dto.res.UserBoardWithStudentInfoResponseData
import team.msg.sms.domain.authentication.exception.MarkingBoardNotFoundException
import team.msg.sms.domain.authentication.model.MarkingBoard
import team.msg.sms.domain.authentication.model.MarkingBoardType
import team.msg.sms.domain.authentication.service.GetMarkingBoardService
import team.msg.sms.domain.authentication.spi.MarkingBoardPort
import java.util.UUID

@Service
class GetMarkingBoardServiceImpl(
    private val markingBoardPort: MarkingBoardPort
) : GetMarkingBoardService {
    override fun getMarkingBoardById(id: UUID): MarkingBoard {
        return markingBoardPort.queryMarkingBoardById(id) ?: throw MarkingBoardNotFoundException
    }

    override fun getMarkingBoardByStudentIds(
        studentIds: List<UUID>,
        authenticationId: UUID,
        page: Int,
        size: Int
    ): UserBoardPageResponseData {
        return markingBoardPort.queryMarkingBoardByStudentIds(studentIds, authenticationId, page, size)
    }

    override fun getMarkingBoardByStudentIdsWithType(
        studentIds: List<UUID>,
        authenticationId: UUID,
        page: Int,
        size: Int,
        type: List<MarkingBoardType>
    ): UserBoardPageResponseData {
        return markingBoardPort.queryMarkingBoardByStudentIdsWithType(
            studentIds = studentIds,
            authenticationId = authenticationId,
            page = page,
            size = size,
            type = type
        )
    }

}