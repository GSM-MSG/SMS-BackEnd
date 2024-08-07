package team.msg.sms.persistence.authentication

import org.springframework.stereotype.Component
import team.msg.sms.domain.authentication.model.AuthenticationField
import team.msg.sms.domain.authentication.spi.AuthenticationFieldPort
import team.msg.sms.persistence.authentication.mapper.toDomain
import team.msg.sms.persistence.authentication.mapper.toEntity
import team.msg.sms.persistence.authentication.repository.AuthenticationFieldJpaRepository
import java.util.*

@Component
class AuthenticationFieldPersistenceAdapter(
    private val authenticationFieldJpaRepository: AuthenticationFieldJpaRepository
) : AuthenticationFieldPort {
    override fun queryAuthenticationFieldsByGroupId(groupId: UUID): List<AuthenticationField> =
        authenticationFieldJpaRepository.findAllByGroupIdOrderBySortAsc(groupId).map { it.toDomain() }


    override fun save(authenticationField: AuthenticationField): AuthenticationField =
        authenticationFieldJpaRepository.save(authenticationField.toEntity()).toDomain()
}