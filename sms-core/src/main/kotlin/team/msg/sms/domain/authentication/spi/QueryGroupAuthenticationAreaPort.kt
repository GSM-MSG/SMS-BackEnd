package team.msg.sms.domain.authentication.spi

import team.msg.sms.domain.authentication.model.GroupAuthenticationArea

interface QueryGroupAuthenticationAreaPort {
    fun queryGroupAuthenticationArea(): List<GroupAuthenticationArea>
}