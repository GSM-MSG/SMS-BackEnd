package team.msg.sms.global.gauth

import gauth.GAuth
import gauth.GAuthToken
import gauth.GAuthUserInfo
import org.springframework.stereotype.Component
import team.msg.sms.common.spi.GAuthPort

@Component
class GAuthAdapter(
    private val gAuth: GAuth,
    private val gAuthProperties: GAuthProperties
) : GAuthPort {
    override fun receiveGAuthToken(code: String): GAuthToken =
        gAuth.generateToken(
            code,
            gAuthProperties.clientId,
            gAuthProperties.clientSecret,
            gAuthProperties.redirectUri
        )

    override fun receiveUserInfo(accessToken: String): GAuthUserInfo =
        gAuth.getUserInfo(accessToken)
}