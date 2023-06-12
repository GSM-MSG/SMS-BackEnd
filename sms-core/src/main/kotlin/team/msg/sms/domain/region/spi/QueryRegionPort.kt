package team.msg.sms.domain.region.spi

import team.msg.sms.domain.region.model.Region
import java.util.*

interface QueryRegionPort {
    fun findByStudentUuid(uuid: UUID): List<Region>
}