package team.msg.sms.domain.authentication.service

import java.util.UUID

interface CheckUserFormValueService {
    fun checkUserFormValueBySetIds(setIds: List<UUID>): Boolean
}