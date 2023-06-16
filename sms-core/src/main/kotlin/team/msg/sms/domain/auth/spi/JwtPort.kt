package team.msg.sms.domain.auth.spi

import team.msg.sms.domain.auth.dto.res.TokenResponseData
import team.msg.sms.domain.auth.model.Role
import java.util.UUID

interface JwtPort {
    fun receiveToken(userId: UUID, role: Role): TokenResponseData
}