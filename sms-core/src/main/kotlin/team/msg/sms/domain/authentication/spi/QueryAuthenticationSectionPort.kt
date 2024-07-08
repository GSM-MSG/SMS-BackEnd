package team.msg.sms.domain.authentication.spi

import team.msg.sms.domain.authentication.model.AuthenticationSection
import java.util.UUID

interface QueryAuthenticationSectionPort {
    fun queryAuthenticationSectionByGroupIds(groupIds: List<UUID>): List<AuthenticationSection>
    fun queryMaxCountById(id: UUID): Int?
}