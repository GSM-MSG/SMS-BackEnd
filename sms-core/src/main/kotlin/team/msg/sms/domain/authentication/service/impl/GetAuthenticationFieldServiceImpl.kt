package team.msg.sms.domain.authentication.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.authentication.model.AuthenticationField
import team.msg.sms.domain.authentication.service.GetAuthenticationFieldService
import team.msg.sms.domain.authentication.spi.AuthenticationFieldPort
import java.util.*

@Service
class GetAuthenticationFieldServiceImpl(
    private val authenticationFieldPort: AuthenticationFieldPort
) : GetAuthenticationFieldService {
    override fun getAuthenticationFieldsBySectionId(sectionId: UUID): List<AuthenticationField> =
        authenticationFieldPort.queryAuthenticationFieldsBySectionId(sectionId)
}