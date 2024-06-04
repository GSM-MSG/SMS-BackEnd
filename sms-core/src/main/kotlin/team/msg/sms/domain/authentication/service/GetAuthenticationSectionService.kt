package team.msg.sms.domain.authentication.service

import team.msg.sms.domain.authentication.model.AuthenticationSection
import java.util.UUID

interface GetAuthenticationSectionService {
    fun getAuthenticationSectionByGroupIds(groupIds: List<UUID>): List<AuthenticationSection>
}