package team.msg.sms.domain.authentication.service

import team.msg.sms.domain.authentication.model.AuthenticationArea
import java.util.UUID

interface GetAuthenticationAreaService {
    fun getGroupAuthenticationAreaByAuthenticationFormId(authenticationFormId: UUID): List<AuthenticationArea>
}