package team.msg.sms.domain.authentication.spi

import team.msg.sms.domain.authentication.model.AuthenticationArea

interface CommandAuthenticationAreaPort {
    fun save(groupAuthenticationArea: AuthenticationArea): AuthenticationArea
}