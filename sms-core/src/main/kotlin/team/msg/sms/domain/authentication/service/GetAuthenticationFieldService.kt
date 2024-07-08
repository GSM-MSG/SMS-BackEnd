package team.msg.sms.domain.authentication.service

import team.msg.sms.domain.authentication.model.AuthenticationField
import java.util.UUID

interface GetAuthenticationFieldService {
    fun getAuthenticationFieldsByGroupId(sectionId: UUID) : List<AuthenticationField>
}