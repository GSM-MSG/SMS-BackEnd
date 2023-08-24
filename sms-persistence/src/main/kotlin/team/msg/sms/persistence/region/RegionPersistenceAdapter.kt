package team.msg.sms.persistence.region

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.msg.sms.domain.region.model.Region
import team.msg.sms.domain.region.spi.RegionPort
import team.msg.sms.domain.student.exception.StudentNotFoundException
import team.msg.sms.domain.student.model.Student
import team.msg.sms.persistence.region.mapper.toDomain
import team.msg.sms.persistence.region.mapper.toEntity
import team.msg.sms.persistence.region.repository.RegionJpaRepository
import team.msg.sms.persistence.student.repository.StudentJpaRepository
import java.util.*

@Component
class RegionPersistenceAdapter(
    private val regionJpaRepository: RegionJpaRepository,
    private val studentJpaRepository: StudentJpaRepository
) : RegionPort {
    override fun saveAll(regions: List<Region>): List<Region> {
        val student = studentJpaRepository.findByIdOrNull(regions.first().studentId)
            ?: throw StudentNotFoundException
        return regionJpaRepository.saveAll(regions
            .map { it.toEntity(student) })
            .map { it.toDomain() }
    }

    override fun deleteAllByStudent(student: Student) {
        val student = studentJpaRepository.findByIdOrNull(student.id)
            ?: throw StudentNotFoundException
        regionJpaRepository.deleteAllByStudent(student)
    }

    override fun queryByStudentUuid(uuid: UUID): List<Region> =
        regionJpaRepository.findByStudentId(uuid).map { it.toDomain() }

    override fun deleteByRegion(region: Region, student: Student) {
        val student = studentJpaRepository.findByIdOrNull(student.id)
            ?: throw StudentNotFoundException
        regionJpaRepository.delete(region.toEntity(student))
    }
}