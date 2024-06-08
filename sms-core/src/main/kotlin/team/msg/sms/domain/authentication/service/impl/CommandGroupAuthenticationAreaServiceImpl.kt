package team.msg.sms.domain.authentication.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.authentication.model.GroupAuthenticationArea
import team.msg.sms.domain.authentication.service.CommandGroupAuthenticationAreaService
import team.msg.sms.domain.authentication.spi.CommandGroupAuthenticationAreaPort

@Service
class CommandGroupAuthenticationAreaServiceImpl(
    private val groupAuthenticationAreaPort: CommandGroupAuthenticationAreaPort
) : CommandGroupAuthenticationAreaService {
    override fun save(groupAuthenticationArea: GroupAuthenticationArea): GroupAuthenticationArea =
        groupAuthenticationAreaPort.save(groupAuthenticationArea)
}