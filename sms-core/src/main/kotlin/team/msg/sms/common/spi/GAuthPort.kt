package team.msg.sms.common.spi

import gauth.GAuthToken
import gauth.GAuthUserInfo

interface GAuthPort {
    fun receiveGAuthToken(code: String): GAuthToken

    fun receiveUserInfo(accessToken: String): GAuthUserInfo
}