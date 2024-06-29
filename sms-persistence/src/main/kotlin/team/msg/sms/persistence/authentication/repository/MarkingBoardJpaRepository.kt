package team.msg.sms.persistence.authentication.repository

import org.springframework.data.repository.CrudRepository
import team.msg.sms.persistence.authentication.entity.MarkingBoardJpaEntity
import java.util.UUID

interface MarkingBoardJpaRepository: CrudRepository<MarkingBoardJpaEntity, UUID> {
}