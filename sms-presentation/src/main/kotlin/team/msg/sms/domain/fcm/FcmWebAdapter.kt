package team.msg.sms.domain.fcm

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.msg.sms.common.model.Fcm
import team.msg.sms.common.service.FcmService
import team.msg.sms.domain.fcm.dto.req.FcmDto

@RestController
@RequestMapping("/fcm")
class FcmWebAdapter(
    private val fcmService: FcmService
) {
    @PostMapping("/send")
    fun sendMessage(@RequestBody fcmDto: FcmDto): ResponseEntity<Int> {
        val result = fcmService.sendMessageTo(toFcm(fcmDto))
        return ResponseEntity.ok(result)
    }

    fun toFcm(fcmDto: FcmDto) =
        Fcm(
            body = fcmDto.body,
            title = fcmDto.title,
            token = fcmDto.token
        )
}