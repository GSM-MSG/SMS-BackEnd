package team.msg.sms.domain.auth.service

import team.msg.sms.domain.auth.model.RefreshToken

interface CommandAuthService {
    fun deleteRefreshToken(refreshToken: RefreshToken)
}