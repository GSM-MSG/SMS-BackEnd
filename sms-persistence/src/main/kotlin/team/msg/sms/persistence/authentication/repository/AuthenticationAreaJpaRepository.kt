package team.msg.sms.persistence.authentication.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import team.msg.sms.persistence.authentication.entity.AuthenticationAreaJpaEntity
import java.util.UUID

@Repository
interface AuthenticationAreaJpaRepository : JpaRepository<AuthenticationAreaJpaEntity, UUID> {
    fun findAuthenticationAreaJpaEntitiesByAuthenticationFormId(authenticationFormId: UUID): List<AuthenticationAreaJpaEntity>
}