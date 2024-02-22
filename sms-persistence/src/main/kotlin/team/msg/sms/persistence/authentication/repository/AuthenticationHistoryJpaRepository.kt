package team.msg.sms.persistence.authentication.repository

import org.springframework.data.jpa.repository.JpaRepository

interface AuthenticationHistoryJpaRepository : JpaRepository<AuthenticationHistoryJpaRepository, Long>