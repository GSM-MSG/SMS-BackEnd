package team.msg.sms.persistence.authentication

import org.springframework.stereotype.Component
import team.msg.sms.domain.authentication.model.AuthenticationSection
import team.msg.sms.domain.authentication.spi.AuthenticationSectionPort
import team.msg.sms.persistence.authentication.mapper.toDomain
import team.msg.sms.persistence.authentication.repository.AuthenticationSectionRepositoryCustom
import java.util.*

@Component
class AuthenticationSectionPersistenceAdapter(
    private val authenticationSectionRepositoryCustom: AuthenticationSectionRepositoryCustom
) : AuthenticationSectionPort {
    override fun getAuthenticationSectionByGroupIds(groupIds: List<UUID>): List<AuthenticationSection> =
        authenticationSectionRepositoryCustom.getAuthenticationSectionByGroupIds(groupIds).map { it.toDomain() }

}