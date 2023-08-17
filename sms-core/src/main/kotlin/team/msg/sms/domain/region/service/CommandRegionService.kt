package team.msg.sms.domain.region.service

import team.msg.sms.domain.region.model.Region
import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.user.model.User

interface CommandRegionService {
    fun saveAll(region: List<Region>, student: Student, user: User): List<Region>
    fun deleteAllByStudent(student: Student)
}