package team.msg.sms.persistence.authentication.repository

import org.springframework.data.jpa.repository.JpaRepository
import team.msg.sms.persistence.authentication.entity.AuthenticationJpaEntity
import java.util.*

interface AuthenticationJpaRepository : JpaRepository<AuthenticationJpaEntity, UUID>