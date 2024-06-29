package team.msg.sms.persistence.authentication.repository

import org.springframework.data.jpa.repository.JpaRepository
import team.msg.sms.persistence.authentication.entity.MarkingValueJpaEntity
import java.util.UUID

interface MarkingValueJpaRepository: JpaRepository<MarkingValueJpaEntity, UUID> {
}