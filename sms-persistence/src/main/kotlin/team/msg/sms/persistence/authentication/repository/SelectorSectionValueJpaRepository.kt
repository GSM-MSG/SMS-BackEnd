package team.msg.sms.persistence.authentication.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import team.msg.sms.persistence.authentication.entity.SelectorSectionValueJpaEntity
import java.util.UUID

@Repository
interface SelectorSectionValueJpaRepository : CrudRepository<SelectorSectionValueJpaEntity, UUID> {
}