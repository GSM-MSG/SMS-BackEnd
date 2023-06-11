package team.msg.sms.common.spi

import java.util.UUID

interface SecurityPort {
    fun getCurrentUserId(): UUID

    fun getCurrentUserRole(): String
}