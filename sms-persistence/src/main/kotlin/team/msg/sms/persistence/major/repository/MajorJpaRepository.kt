package team.msg.sms.persistence.major.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import team.msg.sms.persistence.major.entity.MajorJpaEntity

@Repository
interface MajorJpaRepository : CrudRepository<MajorJpaEntity, Long>