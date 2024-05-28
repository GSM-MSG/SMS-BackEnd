package team.msg.sms.domain.authentication.event

import team.msg.sms.domain.authentication.model.Authentication
import java.util.UUID

/**
 * 인증제 히스토리를 저장하는 event입니다.
 * 인증제 활동이 저장되거나 변경될 때 발행됩니다.
 */
data class AuthenticationHistoryEvent (
    val authentication: Authentication,
    val reason: String,
    val teacherId: UUID? = null
)