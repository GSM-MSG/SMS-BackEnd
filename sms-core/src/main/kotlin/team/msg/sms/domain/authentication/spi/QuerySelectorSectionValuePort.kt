package team.msg.sms.domain.authentication.spi

import team.msg.sms.domain.authentication.model.SelectorSectionValue

interface QuerySelectorSectionValuePort {
    fun querySelectorSectionValue(): List<SelectorSectionValue>
}