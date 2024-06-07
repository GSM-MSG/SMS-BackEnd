package team.msg.sms.domain.authentication.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.authentication.model.SelectorSectionValue
import team.msg.sms.domain.authentication.service.CommandSelectorSectionValueService
import team.msg.sms.domain.authentication.spi.CommandSelectorSectionValuePort

@Service
class CommandSelectorSectionValueServiceImpl(
    private val selectorSectionValuePort: CommandSelectorSectionValuePort
) : CommandSelectorSectionValueService {
    override fun saveAll(selectorSectionValueList: List<SelectorSectionValue>) {
        selectorSectionValuePort.saveAll(selectorSectionValueList)
    }
}