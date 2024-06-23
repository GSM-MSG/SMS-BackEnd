package team.msg.sms.common.service

import org.springframework.stereotype.Component
import team.msg.sms.common.annotation.Service
import team.msg.sms.common.model.Fcm

@Service
@Component
interface FcmService {
    fun sendMessageTo(fcmDto: Fcm): Int
}