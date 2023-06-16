package team.msg.sms.domain.region.spi

import team.msg.sms.domain.region.model.Region
import java.util.*

interface QueryRegionPort {
    fun queryByStudentUuid(uuid: UUID): List<Region>
}