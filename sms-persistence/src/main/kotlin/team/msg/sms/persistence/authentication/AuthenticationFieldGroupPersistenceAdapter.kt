package team.msg.sms.persistence.authentication

import org.springframework.stereotype.Component
import team.msg.sms.domain.authentication.model.AuthenticationFieldGroup
import team.msg.sms.domain.authentication.spi.AuthenticationFieldGroupPort
import team.msg.sms.persistence.authentication.mapper.toDomain
import team.msg.sms.persistence.authentication.mapper.toEntity
import team.msg.sms.persistence.authentication.repository.AuthenticationFieldGroupJpaRepository
import java.util.*

@Component
class AuthenticationFieldGroupPersistenceAdapter(
    private val authenticationFieldGroupJpaRepository: AuthenticationFieldGroupJpaRepository
) : AuthenticationFieldGroupPort {
    override fun save(authenticationFieldGroup: AuthenticationFieldGroup): AuthenticationFieldGroup =
        authenticationFieldGroupJpaRepository.save(authenticationFieldGroup.toEntity()).toDomain()

    override fun queryAuthenticationFieldGroupBySectionId(sectionId: UUID): List<AuthenticationFieldGroup> {
        return authenticationFieldGroupJpaRepository.findAllBySectionId(sectionId).map { it.toDomain() }
    }
}