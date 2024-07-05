package team.msg.sms.domain.authentication.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.authentication.model.MarkingValue
import team.msg.sms.domain.authentication.service.CommandMarkingValueService
import team.msg.sms.domain.authentication.spi.MarkingValuePort

@Service
class CommandMarkingServiceValueImpl(
    private val markingValuePort: MarkingValuePort
) : CommandMarkingValueService {
    override fun saveAll(markingValueList: List<MarkingValue>): List<MarkingValue> =
        markingValuePort.saveAll(markingValueList)
}