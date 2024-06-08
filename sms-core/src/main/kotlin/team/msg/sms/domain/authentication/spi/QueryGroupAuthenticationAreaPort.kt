package team.msg.sms.domain.authentication.spi

import team.msg.sms.domain.authentication.model.GroupAuthenticationArea
import java.util.UUID

interface QueryGroupAuthenticationAreaPort {
    fun queryGroupAuthenticationAreaByAuthenticationFormId(authenticationFormId: UUID): List<GroupAuthenticationArea>
}