package team.msg.sms.common.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.common.service.SecurityService
import team.msg.sms.common.spi.SecurityPort
import java.util.*

@Service
class SecurityServiceImpl(
    private val securityPort: SecurityPort
) : SecurityService {
    override fun getCurrentUserId(): UUID =
        securityPort.getCurrentUserId()

    override fun getCurrentUserRole(): String =
        securityPort.getCurrentUserRole()
}