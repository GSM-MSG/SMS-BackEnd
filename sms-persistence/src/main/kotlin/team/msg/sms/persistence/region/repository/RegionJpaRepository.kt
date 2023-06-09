package team.msg.sms.persistence.region.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import team.msg.sms.persistence.region.entity.RegionJpaEntity
import team.msg.sms.persistence.student.entity.StudentJpaEntity
import java.util.UUID

@Repository
interface RegionJpaRepository : CrudRepository<RegionJpaEntity, Long>{
    fun findByStudentId(uuid: UUID): List<RegionJpaEntity>
    fun deleteAllByStudent(studentJpaEntity: StudentJpaEntity)
}