package team.msg.sms.domain.authentication.service

import team.msg.sms.domain.authentication.model.GroupAuthenticationArea
import java.util.UUID

interface GetGroupAuthenticationAreaService {
    fun getGroupAuthenticationArea(): List<GroupAuthenticationArea>
}