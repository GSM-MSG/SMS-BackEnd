package team.msg.sms.domain.authentication.service

import team.msg.sms.common.annotation.Service

@Service
class MarkingBoardService(
    getMarkingBoardService: GetMarkingBoardService,
    commandMarkingBoardService: CommandMarkingBoardService
) : GetMarkingBoardService by getMarkingBoardService,
    CommandMarkingBoardService by commandMarkingBoardService