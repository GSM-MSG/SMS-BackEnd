package team.msg.sms.domain.authentication.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.authentication.model.GroupAuthenticationArea
import team.msg.sms.domain.authentication.service.GetGroupAuthenticationAreaService
import team.msg.sms.domain.authentication.spi.GroupAuthenticationAreaPort

@Service
class GetGroupAuthenticationAreaServiceImpl(
    private val groupAuthenticationAreaPort: GroupAuthenticationAreaPort
) : GetGroupAuthenticationAreaService {
    override fun getGroupAuthenticationArea(): List<GroupAuthenticationArea> =
        groupAuthenticationAreaPort.queryGroupAuthenticationArea()

}