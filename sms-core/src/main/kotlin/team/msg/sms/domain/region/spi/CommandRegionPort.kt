package team.msg.sms.domain.region.spi

import team.msg.sms.domain.region.model.Region
import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.user.model.User

interface CommandRegionPort {
    fun saveAll(region: List<Region>, student: Student, user: User): List<Region>
}