package team.msg.sms.domain.authentication.service

import team.msg.sms.domain.authentication.model.GroupAuthenticationArea

interface CommandGroupAuthenticationAreaService {
    fun save(groupAuthenticationArea: GroupAuthenticationArea): GroupAuthenticationArea
}