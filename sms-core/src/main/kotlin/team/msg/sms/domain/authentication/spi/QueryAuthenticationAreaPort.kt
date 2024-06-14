package team.msg.sms.domain.authentication.spi

import team.msg.sms.domain.authentication.model.AuthenticationArea
import java.util.UUID

interface QueryAuthenticationAreaPort {
    fun queryGroupAuthenticationAreaByAuthenticationFormId(authenticationFormId: UUID): List<AuthenticationArea>
}