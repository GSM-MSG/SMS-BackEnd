package team.msg.sms.domain.authentication.service

import team.msg.sms.domain.authentication.model.SelectorSectionValue

interface GetSelectorSectionValueService {
    fun getSelectorSectionValue(): List<SelectorSectionValue>
}