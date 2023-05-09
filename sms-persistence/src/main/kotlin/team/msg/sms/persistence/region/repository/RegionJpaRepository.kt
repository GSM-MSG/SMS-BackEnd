package team.msg.sms.persistence.region.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import team.msg.sms.persistence.region.entity.RegionJpaEntity

@Repository
interface RegionJpaRepository : CrudRepository<RegionJpaEntity, Long>{
}