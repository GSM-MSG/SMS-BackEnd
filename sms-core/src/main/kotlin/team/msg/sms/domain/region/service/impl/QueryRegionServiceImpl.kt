package team.msg.sms.domain.region.service.impl

import org.springframework.stereotype.Service
import team.msg.sms.domain.region.model.Region
import team.msg.sms.domain.region.service.QueryRegionService
import team.msg.sms.domain.region.spi.RegionPort
import java.util.*

@Service
class QueryRegionServiceImpl(
    private val regionPort: RegionPort
) : QueryRegionService {
    override fun getRegionByStudentUuid(uuid: UUID): List<Region> =
        regionPort.findByStudentUuid(uuid)
}