package team.msg.sms.domain.auth.service

import team.msg.sms.domain.auth.model.RefreshToken

interface GetAuthService {
    fun getRefreshTokenByToken(token: String): RefreshToken
}