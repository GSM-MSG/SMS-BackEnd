package team.msg.sms.persistence.authentication

import org.springframework.stereotype.Component
import team.msg.sms.domain.authentication.model.AuthenticationSection
import team.msg.sms.domain.authentication.spi.AuthenticationSectionPort
import team.msg.sms.persistence.authentication.mapper.toDomain
import team.msg.sms.persistence.authentication.mapper.toEntity
import team.msg.sms.persistence.authentication.repository.AuthenticationSectionJpaRepository
import team.msg.sms.persistence.authentication.repository.AuthenticationSectionRepositoryCustom
import java.util.*

@Component
class AuthenticationSectionPersistenceAdapter(
    private val authenticationSectionRepositoryCustom: AuthenticationSectionRepositoryCustom,
    private val authenticationSectionJpaRepository: AuthenticationSectionJpaRepository
) : AuthenticationSectionPort {
    override fun getAuthenticationSectionByGroupIds(groupIds: List<UUID>): List<AuthenticationSection> =
        authenticationSectionRepositoryCustom.getAuthenticationSectionByGroupIds(groupIds).map { it.toDomain() }

    override fun getMaxCountById(id: UUID): Int? = authenticationSectionRepositoryCustom.findMaxCountById(id)


    override fun save(authenticationSection: AuthenticationSection): AuthenticationSection =
        authenticationSectionJpaRepository.save(authenticationSection.toEntity()).toDomain()

}