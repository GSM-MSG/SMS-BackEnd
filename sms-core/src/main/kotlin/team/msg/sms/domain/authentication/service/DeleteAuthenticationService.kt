package team.msg.sms.domain.authentication.service

import java.util.UUID

interface DeleteAuthenticationService {
    fun deleteAuthenticationByUuid(uuid: UUID)
}