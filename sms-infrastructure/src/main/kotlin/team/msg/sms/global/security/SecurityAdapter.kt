package team.msg.sms.global.security

import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import team.msg.sms.common.spi.SecurityPort
import java.util.*


@Component
class SecurityAdapter(
) : SecurityPort {
    override fun getCurrentUserId(): UUID =
        UUID.fromString(SecurityContextHolder.getContext().authentication.name)

    override fun getCurrentUserRole(): String =
        SecurityContextHolder.getContext().authentication.authorities.first().authority

    override fun isCurrentAnonymous(): Boolean {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        return authentication is AnonymousAuthenticationToken
    }
}