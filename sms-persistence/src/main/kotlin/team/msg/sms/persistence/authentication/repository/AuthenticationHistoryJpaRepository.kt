package team.msg.sms.persistence.authentication.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthenticationHistoryJpaRepository : JpaRepository<AuthenticationHistoryJpaRepository, Long>