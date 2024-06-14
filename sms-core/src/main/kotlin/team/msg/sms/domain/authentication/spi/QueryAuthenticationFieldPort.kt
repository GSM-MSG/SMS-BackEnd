package team.msg.sms.domain.authentication.spi

import team.msg.sms.domain.authentication.model.AuthenticationField
import java.util.UUID

interface QueryAuthenticationFieldPort {
    fun queryAuthenticationFieldsBySectionId(sectionId: UUID): List<AuthenticationField>
}