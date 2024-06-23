package team.msg.sms.domain.fcm.dto.req

data class FcmDto(
    val token: String,
    val title: String,
    val body: String
)
