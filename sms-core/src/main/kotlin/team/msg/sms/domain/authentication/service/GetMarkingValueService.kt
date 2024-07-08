package team.msg.sms.domain.authentication.service

import team.msg.sms.domain.authentication.model.MarkingValue
import java.util.UUID

interface GetMarkingValueService {
    fun findMarkingValueListByMarkingBoardId(markingBoardId: UUID): List<MarkingValue>
}