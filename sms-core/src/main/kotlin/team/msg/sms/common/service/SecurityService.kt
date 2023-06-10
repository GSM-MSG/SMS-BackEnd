package team.msg.sms.common.service

import team.msg.sms.domain.auth.model.Role
import java.util.*

interface SecurityService {
    fun getCurrentUserId(): UUID

    fun getCurrentUserRole(): String
}