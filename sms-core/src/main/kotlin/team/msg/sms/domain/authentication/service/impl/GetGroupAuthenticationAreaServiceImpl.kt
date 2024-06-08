package team.msg.sms.domain.authentication.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.authentication.model.GroupAuthenticationArea
import team.msg.sms.domain.authentication.service.GetGroupAuthenticationAreaService
import team.msg.sms.domain.authentication.spi.GroupAuthenticationAreaPort
import java.util.UUID

@Service
class GetGroupAuthenticationAreaServiceImpl(
    private val groupAuthenticationAreaPort: GroupAuthenticationAreaPort
) : GetGroupAuthenticationAreaService {
    override fun getGroupAuthenticationAreaByAuthenticationFormId(authenticationFormId: UUID): List<GroupAuthenticationArea> =
        groupAuthenticationAreaPort.queryGroupAuthenticationAreaByAuthenticationFormId(authenticationFormId)

}