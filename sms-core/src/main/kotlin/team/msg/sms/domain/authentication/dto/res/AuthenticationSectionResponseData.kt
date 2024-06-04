package team.msg.sms.domain.authentication.dto.res

data class AuthenticationSectionResponseData(
    val section: String,
    val scoreDescription: String,
    val sectionScore: Int,
    val maxCount: Int,
    val fields: List<AuthenticationSectionFieldResponseData>
)
