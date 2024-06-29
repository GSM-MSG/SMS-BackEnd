package team.msg.sms.domain.authentication.spi

import java.util.UUID

interface QueryAuthenticationFormPort {
    fun queryActiveAuthenticationFormId(): UUID
}