package team.msg.sms.domain.authentication.spi

import team.msg.sms.domain.authentication.model.MarkingBoard

interface CommandMarkingBoardPort {
    fun save(markingBoard: MarkingBoard)
}