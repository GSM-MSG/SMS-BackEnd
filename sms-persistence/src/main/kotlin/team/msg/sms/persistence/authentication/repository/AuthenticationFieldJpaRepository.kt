package team.msg.sms.persistence.authentication.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import team.msg.sms.persistence.authentication.entity.AuthenticationFieldJpaEntity
import java.util.UUID

@Repository
interface AuthenticationFieldJpaRepository: CrudRepository<AuthenticationFieldJpaEntity, UUID> {
    fun findAllBySectionId(sectionId: UUID): List<AuthenticationFieldJpaEntity>
}