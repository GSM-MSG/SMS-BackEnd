package team.msg.sms.domain.authentication.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.authentication.model.SelectorSectionValue
import team.msg.sms.domain.authentication.service.GetSelectorSectionValueService
import team.msg.sms.domain.authentication.spi.SelectorSectionValuePort

@Service
class GetSelectorSectionValueServiceImpl(
    private val selectorSectionValuePort: SelectorSectionValuePort
) : GetSelectorSectionValueService {
    override fun getSelectorSectionValue(): List<SelectorSectionValue> =
        selectorSectionValuePort.querySelectorSectionValue()
}