package team.msg.sms.domain.region.service

import team.msg.sms.domain.region.model.Region
import java.util.UUID

interface QueryRegionService {
    fun getRegionByStudentUuid(uuid: UUID): List<Region>
}