package team.msg.sms.domain.authentication.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.authentication.model.AuthenticationArea
import team.msg.sms.domain.authentication.service.GetAuthenticationAreaService
import team.msg.sms.domain.authentication.spi.AuthenticationAreaPort
import java.util.UUID

@Service
class GetAuthenticationAreaServiceImpl(
    private val authenticationAreaPort: AuthenticationAreaPort
) : GetAuthenticationAreaService {
    override fun getGroupAuthenticationAreaByAuthenticationFormId(authenticationFormId: UUID): List<AuthenticationArea> =
        authenticationAreaPort.queryGroupAuthenticationAreaByAuthenticationFormId(authenticationFormId)

}