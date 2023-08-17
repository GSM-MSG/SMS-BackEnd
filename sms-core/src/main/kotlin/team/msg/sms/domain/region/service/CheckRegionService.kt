package team.msg.sms.domain.region.service

import team.msg.sms.domain.region.model.Region

interface CheckRegionService {
    fun checkAddedRegion(regions: List<Region>, modifyRegions: List<String>): List<String>

    fun checkRemovedRegion(regions: List<Region>, modifyRegions: List<String>): List<Region>
}