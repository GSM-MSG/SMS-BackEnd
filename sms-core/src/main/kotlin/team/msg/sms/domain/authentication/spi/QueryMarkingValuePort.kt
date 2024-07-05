package team.msg.sms.domain.authentication.spi

import team.msg.sms.domain.authentication.model.MarkingValue
import java.util.UUID

interface QueryMarkingValuePort {
    fun queryMarkingValueListByMarkingBoardId(markingBoardId: UUID): List<MarkingValue>
}