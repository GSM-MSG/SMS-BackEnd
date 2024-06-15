package team.msg.sms.domain.authentication.service

import team.msg.sms.domain.authentication.model.AuthenticationArea

interface CommandAuthenticationAreaService {
    fun save(groupAuthenticationArea: AuthenticationArea): AuthenticationArea
}