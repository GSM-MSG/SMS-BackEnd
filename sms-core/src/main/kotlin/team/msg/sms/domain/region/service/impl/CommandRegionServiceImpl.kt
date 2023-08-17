package team.msg.sms.domain.region.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.region.model.Region
import team.msg.sms.domain.region.service.CommandRegionService
import team.msg.sms.domain.region.spi.RegionPort
import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.user.model.User

@Service
class CommandRegionServiceImpl(
    private val regionPort: RegionPort
) : CommandRegionService {
    override fun saveAll(region: List<Region>, student: Student, user: User): List<Region> =
        regionPort.saveAll(region, student, user)

    override fun deleteAllByStudent(student: Student) =
        regionPort.deleteAllByStudent(student)

    override fun deleteByRegion(region: Region, student: Student) {
        regionPort.deleteByRegion(region, student)
    }
}