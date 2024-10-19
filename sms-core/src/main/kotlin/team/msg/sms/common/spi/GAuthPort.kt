package team.msg.sms.common.spi

import gauth.response.GAuthToken
import gauth.response.GAuthUserInfo

interface GAuthPort {
    fun receiveGAuthToken(code: String): GAuthToken

    fun receiveUserInfo(accessToken: String): GAuthUserInfo
}