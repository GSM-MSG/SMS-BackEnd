package team.msg.sms.common.service

import java.util.*

interface SecurityService {
    fun getCurrentUserId(): UUID

    fun getCurrentUserRole(): String
}