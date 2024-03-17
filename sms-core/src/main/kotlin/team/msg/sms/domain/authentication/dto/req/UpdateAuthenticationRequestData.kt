package team.msg.sms.domain.authentication.dto.req

data class UpdateAuthenticationRequestData (
    val title: String,
    val content: String,
    val activityImages: List<String>
)