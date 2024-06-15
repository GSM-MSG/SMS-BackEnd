package team.msg.sms.domain.authentication.spi

import team.msg.sms.domain.authentication.model.AuthenticationSection
import java.util.UUID

interface QueryAuthenticationSectionPort {
    fun getAuthenticationSectionByGroupIds(groupIds: List<UUID>): List<AuthenticationSection>
    fun getMaxCountById(id: UUID): Int?
}