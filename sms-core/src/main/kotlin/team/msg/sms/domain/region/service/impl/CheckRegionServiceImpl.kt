package team.msg.sms.domain.region.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.region.model.Region
import team.msg.sms.domain.region.service.CheckRegionService

@Service
class CheckRegionServiceImpl : CheckRegionService {
    override fun checkAddedRegion(regions: List<Region>, modifyRegions: List<String>): List<String> =
        modifyRegions
            .filterNot { regions.map { it -> it.region }.contains(it) }

    override fun checkRemovedRegion(regions: List<Region>, modifyRegions: List<String>): List<Region> =
        regions.filterNot { modifyRegions.contains(it.region) }
}