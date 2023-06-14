package team.msg.sms.persistence.region

import org.springframework.stereotype.Component
import team.msg.sms.domain.region.model.Region
import team.msg.sms.domain.region.spi.RegionPort
import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.user.model.User
import team.msg.sms.persistence.region.mapper.toDomain
import team.msg.sms.persistence.region.mapper.toEntity
import team.msg.sms.persistence.region.repository.RegionJpaRepository
import team.msg.sms.persistence.student.mapper.toEntity
import team.msg.sms.persistence.user.mapper.toEntity
import java.util.*

@Component
class RegionPersistenceAdapter(
    private val regionJpaRepository: RegionJpaRepository
) : RegionPort {
    override fun saveAll(region: List<Region>, student: Student, user: User): List<Region> =
        regionJpaRepository.saveAll(region.map { it.toEntity(student.toEntity(user.toEntity())) }).map { it.toDomain() }

    override fun deleteAllByStudent(student: Student, user: User) =
        regionJpaRepository.deleteAllByStudent(student.toEntity(user.toEntity()))

    override fun queryByStudentUuid(uuid: UUID): List<Region> =
        regionJpaRepository.findByStudentId(uuid).map { it.toDomain() }
}