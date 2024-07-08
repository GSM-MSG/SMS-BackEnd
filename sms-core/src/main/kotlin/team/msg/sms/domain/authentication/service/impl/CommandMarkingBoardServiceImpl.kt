package team.msg.sms.domain.authentication.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.authentication.model.MarkingBoard
import team.msg.sms.domain.authentication.service.CommandMarkingBoardService
import team.msg.sms.domain.authentication.spi.MarkingBoardPort

@Service
class CommandMarkingBoardServiceImpl(
    private val markingBoardPort: MarkingBoardPort
): CommandMarkingBoardService {
    override fun save(markingBoard: MarkingBoard) {
        markingBoardPort.save(markingBoard)
    }
}