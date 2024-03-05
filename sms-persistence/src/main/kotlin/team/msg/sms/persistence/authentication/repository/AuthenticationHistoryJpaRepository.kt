package team.msg.sms.persistence.authentication.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import team.msg.sms.persistence.authentication.entity.AuthenticationHistoryJpaEntity
import team.msg.sms.persistence.authentication.entity.AuthenticationJpaEntity

@Repository
interface AuthenticationHistoryJpaRepository : JpaRepository<AuthenticationHistoryJpaEntity, Long> {
    fun findByAuthenticationOrderByCreatedAtDesc(authentication: AuthenticationJpaEntity): AuthenticationHistoryJpaEntity
}