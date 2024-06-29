package team.msg.sms.persistence.authentication

import org.springframework.stereotype.Component
import team.msg.sms.domain.authentication.model.AuthenticationForm
import team.msg.sms.domain.authentication.spi.AuthenticationFormPort
import team.msg.sms.persistence.authentication.mapper.toDomain
import team.msg.sms.persistence.authentication.mapper.toEntity
import team.msg.sms.persistence.authentication.repository.AuthenticationFormJpaRepository
import java.util.*

@Component
class AuthenticationFormPersistenceAdapter(
    private val authenticationFormJpaRepository: AuthenticationFormJpaRepository
) : AuthenticationFormPort {
    override fun save(authenticationForm: AuthenticationForm): AuthenticationForm =
        authenticationFormJpaRepository.save(authenticationForm.toEntity()).toDomain()

    override fun queryActiveAuthenticationFormId(): UUID {
        return authenticationFormJpaRepository.findByActive(true).id
    }
}