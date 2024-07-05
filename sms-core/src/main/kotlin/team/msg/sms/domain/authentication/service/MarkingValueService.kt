package team.msg.sms.domain.authentication.service

import team.msg.sms.common.annotation.Service

@Service
class MarkingValueService(
    commandMarkingService: CommandMarkingValueService,
    getMarkingValueService: GetMarkingValueService
) : CommandMarkingValueService by commandMarkingService,
    GetMarkingValueService by getMarkingValueService