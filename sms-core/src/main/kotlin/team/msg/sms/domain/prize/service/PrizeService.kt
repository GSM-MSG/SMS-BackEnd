package team.msg.sms.domain.prize.service

import team.msg.sms.common.annotation.Service

@Service
class PrizeService(
    commandPrizeService: CommandPrizeService,
    getPrizeService: GetPrizeService,
    checkPrizeService: CheckPrizeService
) : CommandPrizeService by commandPrizeService,
    GetPrizeService by getPrizeService,
    CheckPrizeService by checkPrizeService