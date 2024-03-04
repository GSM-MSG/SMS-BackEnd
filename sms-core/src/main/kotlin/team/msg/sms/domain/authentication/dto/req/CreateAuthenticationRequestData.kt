package team.msg.sms.domain.authentication.dto.req

data class CreateAuthenticationRequestData (
    val title: String,
    val content: String,
    val activityImages: List<String>
)