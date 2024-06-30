package team.msg.sms.domain.authentication.service

import team.msg.sms.domain.authentication.model.MarkingBoard

interface CommandMarkingBoardService {
    fun save(markingBoard: MarkingBoard)
}