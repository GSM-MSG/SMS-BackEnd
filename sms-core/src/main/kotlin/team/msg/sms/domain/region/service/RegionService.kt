package team.msg.sms.domain.region.service

import team.msg.sms.common.annotation.Service

@Service
class RegionService(
    commandRegionService: CommandRegionService,
    queryRegionService: GetRegionService
) : CommandRegionService by commandRegionService,
    GetRegionService by queryRegionService
