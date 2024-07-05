package team.msg.sms.domain.authentication.service

import team.msg.sms.domain.authentication.model.MarkingValue

interface CommandMarkingValueService {
    fun saveAll(markingValueList: List<MarkingValue>): List<MarkingValue>
}