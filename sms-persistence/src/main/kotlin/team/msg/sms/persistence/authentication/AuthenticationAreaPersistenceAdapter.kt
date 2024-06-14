package team.msg.sms.persistence.authentication


import org.springframework.stereotype.Component
import team.msg.sms.domain.authentication.model.AuthenticationArea
import team.msg.sms.domain.authentication.spi.AuthenticationAreaPort
import team.msg.sms.persistence.authentication.mapper.toDomain
import team.msg.sms.persistence.authentication.mapper.toEntity
import team.msg.sms.persistence.authentication.repository.AuthenticationAreaRepository
import java.util.UUID

@Component
class AuthenticationAreaPersistenceAdapter(
    private val authenticationAreaRepository: AuthenticationAreaRepository
) : AuthenticationAreaPort {
    override fun queryGroupAuthenticationAreaByAuthenticationFormId(authenticationFormId: UUID): List<AuthenticationArea> =
        authenticationAreaRepository.findAuthenticationAreaJpaEntitiesByAuthenticationFormId(authenticationFormId)
            .sortedBy { it.sort }.map { it.toDomain() }

    override fun save(groupAuthenticationArea: AuthenticationArea): AuthenticationArea =
        authenticationAreaRepository.save(groupAuthenticationArea.toEntity()).toDomain()
}