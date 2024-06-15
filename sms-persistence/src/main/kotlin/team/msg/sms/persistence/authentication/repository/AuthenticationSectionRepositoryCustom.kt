package team.msg.sms.persistence.authentication.repository

import org.springframework.stereotype.Repository
import team.msg.sms.persistence.authentication.entity.AuthenticationSectionJpaEntity
import java.util.UUID

interface AuthenticationSectionRepositoryCustom {
    fun getAuthenticationSectionByGroupIds(groupIds: List<UUID>): List<AuthenticationSectionJpaEntity>
    fun findMaxCountById(id: UUID): Int?
}