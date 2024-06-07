package team.msg.sms.domain.authentication.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.authentication.model.AuthenticationSection
import team.msg.sms.domain.authentication.service.CommandAuthenticationSectionService
import team.msg.sms.domain.authentication.spi.AuthenticationSectionPort

@Service
class CommandAuthenticationSectionServiceImpl(
    private val authenticationSectionPort: AuthenticationSectionPort
) : CommandAuthenticationSectionService {
    override fun save(authenticationSection: AuthenticationSection): AuthenticationSection =
        authenticationSectionPort.save(authenticationSection)
}