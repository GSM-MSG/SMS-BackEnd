package team.msg.sms.domain.authentication.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.authentication.model.AuthenticationSection
import team.msg.sms.domain.authentication.service.GetAuthenticationSectionService
import team.msg.sms.domain.authentication.spi.AuthenticationSectionPort
import java.util.*

@Service
class GetAuthenticationSectionServiceImpl(
    private val authenticationSectionPort: AuthenticationSectionPort
) : GetAuthenticationSectionService {
    override fun getAuthenticationSectionByGroupIds(groupIds: List<UUID>): List<AuthenticationSection> =
        authenticationSectionPort.getAuthenticationSectionByGroupIds(groupIds)
}