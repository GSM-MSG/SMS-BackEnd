package team.msg.sms.domain.authentication.service

import team.msg.sms.domain.authentication.model.SelectorSectionValue

interface CommandSelectorSectionValueService {
    fun saveAll(selectorSectionValueList: List<SelectorSectionValue>)
}