package team.msg.sms.domain.region.service

import team.msg.sms.domain.region.model.Region
import java.util.UUID

interface GetRegionService {
    fun getRegionByStudentUuid(uuid: UUID): List<Region>
}