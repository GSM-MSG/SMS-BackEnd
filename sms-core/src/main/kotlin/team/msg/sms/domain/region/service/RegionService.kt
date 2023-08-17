package team.msg.sms.domain.region.service

import team.msg.sms.common.annotation.Service

@Service
class RegionService(
    commandRegionService: CommandRegionService,
    getRegionService: GetRegionService,
    checkRegionService: CheckRegionService
) : CommandRegionService by commandRegionService,
    GetRegionService by getRegionService,
    CheckRegionService by checkRegionService
