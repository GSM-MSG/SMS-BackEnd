package team.msg.sms.persistence.major.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MajorJpaRepository : CrudRepository<MajorJpaEntity, Long>