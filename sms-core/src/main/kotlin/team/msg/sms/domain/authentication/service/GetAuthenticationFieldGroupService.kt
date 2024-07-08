package team.msg.sms.domain.authentication.service

import team.msg.sms.domain.authentication.model.AuthenticationFieldGroup
import java.util.UUID

interface GetAuthenticationFieldGroupService {
    fun findAuthenticationFieldGroupBySectionId(sectionId: UUID): List<AuthenticationFieldGroup>
}