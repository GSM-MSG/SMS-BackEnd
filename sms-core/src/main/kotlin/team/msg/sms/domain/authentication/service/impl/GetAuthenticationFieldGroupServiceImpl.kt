package team.msg.sms.domain.authentication.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.authentication.model.AuthenticationFieldGroup
import team.msg.sms.domain.authentication.service.GetAuthenticationFieldGroupService
import team.msg.sms.domain.authentication.spi.AuthenticationFieldGroupPort
import java.util.*

@Service
class GetAuthenticationFieldGroupServiceImpl(
    private val authenticationFieldGroupPort: AuthenticationFieldGroupPort
) : GetAuthenticationFieldGroupService {
    override fun findAuthenticationFieldGroupBySectionId(sectionId: UUID): List<AuthenticationFieldGroup> =
        authenticationFieldGroupPort.queryAuthenticationFieldGroupBySectionId(sectionId)
}