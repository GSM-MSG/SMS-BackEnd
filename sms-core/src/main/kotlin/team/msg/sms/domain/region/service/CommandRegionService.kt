package team.msg.sms.domain.region.service

import team.msg.sms.domain.region.model.Region
import team.msg.sms.domain.student.model.Student

interface CommandRegionService {
    fun saveAll(region: List<Region>): List<Region>
    fun deleteAllByStudent(student: Student)
    fun deleteByRegion(region: Region, student: Student)
}