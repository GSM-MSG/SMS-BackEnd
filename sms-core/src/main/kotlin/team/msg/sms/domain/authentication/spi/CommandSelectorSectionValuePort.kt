package team.msg.sms.domain.authentication.spi

import team.msg.sms.domain.authentication.model.SelectorSectionValue

interface CommandSelectorSectionValuePort {
    fun saveAll(selectorSectionValueList: List<SelectorSectionValue>)
}