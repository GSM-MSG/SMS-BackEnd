package team.msg.sms.domain.authentication.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.authentication.model.AuthenticationArea
import team.msg.sms.domain.authentication.service.CommandAuthenticationAreaService
import team.msg.sms.domain.authentication.spi.CommandAuthenticationAreaPort

@Service
class CommandAuthenticationAreaServiceImpl(
    private val groupAuthenticationAreaPort: CommandAuthenticationAreaPort
) : CommandAuthenticationAreaService {
    override fun save(groupAuthenticationArea: AuthenticationArea): AuthenticationArea =
        groupAuthenticationAreaPort.save(groupAuthenticationArea)
}