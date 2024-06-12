package team.msg.sms.domain.authentication.dto.res

import team.msg.sms.domain.file.dto.res.FileResponseData

data class AuthenticationAreaFormResponseData(
    val title: String,
    val sections: List<AuthenticationSectionResponseData>
)
