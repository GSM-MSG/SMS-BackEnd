package team.msg.sms.persistence.authentication.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import team.msg.sms.persistence.authentication.entity.AuthenticationFieldGroupJpaEntity
import java.util.UUID

@Repository
interface AuthenticationFieldGroupJpaRepository : CrudRepository<AuthenticationFieldGroupJpaEntity, UUID> {
    fun findAllBySectionId(sectionId: UUID): List<AuthenticationFieldGroupJpaEntity>
}