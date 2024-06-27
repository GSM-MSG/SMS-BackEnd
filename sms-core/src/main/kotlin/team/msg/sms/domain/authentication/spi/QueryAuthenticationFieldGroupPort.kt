package team.msg.sms.domain.authentication.spi

import team.msg.sms.domain.authentication.model.AuthenticationFieldGroup
import java.util.UUID

interface QueryAuthenticationFieldGroupPort {
    fun queryAuthenticationFieldGroupBySectionId(sectionId: UUID): List<AuthenticationFieldGroup>
}