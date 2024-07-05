package team.msg.sms.domain.authentication.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.authentication.model.MarkingValue
import team.msg.sms.domain.authentication.service.GetMarkingValueService
import team.msg.sms.domain.authentication.spi.MarkingValuePort
import java.util.*

@Service
class GetMarkingValueServiceImpl(
    private val markingValuePort: MarkingValuePort
) : GetMarkingValueService {
    override fun findMarkingValueListByMarkingBoardId(markingBoardId: UUID): List<MarkingValue> =
        markingValuePort.queryMarkingValueListByMarkingBoardId(markingBoardId = markingBoardId)
}