package team.msg.sms.domain.authentication.service

import team.msg.sms.domain.authentication.model.AuthenticationField
import java.util.UUID

interface GetAuthenticationFieldService {
    fun getAuthenticationFieldsBySectionId(sectionId: UUID) : List<AuthenticationField>
}