package team.msg.sms.domain.authentication.spi

import team.msg.sms.domain.authentication.model.GroupAuthenticationArea

interface CommandGroupAuthenticationAreaPort {
    fun save(groupAuthenticationArea: GroupAuthenticationArea): GroupAuthenticationArea
}