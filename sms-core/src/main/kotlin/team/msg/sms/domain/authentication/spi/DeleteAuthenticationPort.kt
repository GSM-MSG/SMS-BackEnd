package team.msg.sms.domain.authentication.spi

import team.msg.sms.domain.authentication.model.Authentication
import java.util.UUID

interface DeleteAuthenticationPort {
    fun deleteAuthenticationByUuid(uuid: UUID)
}