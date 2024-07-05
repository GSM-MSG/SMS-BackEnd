package team.msg.sms.domain.authentication.spi

import team.msg.sms.domain.authentication.model.MarkingValue

interface CommandMarkingValuePort {
    fun saveAll(markingValueList: List<MarkingValue>): List<MarkingValue>
}