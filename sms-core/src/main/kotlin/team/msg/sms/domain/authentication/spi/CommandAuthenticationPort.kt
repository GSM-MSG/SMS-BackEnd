package team.msg.sms.domain.authentication.spi

import team.msg.sms.domain.authentication.model.Authentication

interface CommandAuthenticationPort {
    fun save(authentication: Authentication): Authentication
}