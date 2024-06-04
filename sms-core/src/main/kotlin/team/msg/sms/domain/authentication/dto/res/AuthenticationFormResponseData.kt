package team.msg.sms.domain.authentication.dto.res

import team.msg.sms.domain.file.dto.res.FileResponseData

data class AuthenticationFormResponseData(
    val title: String,
    val files: List<FileResponseData>,
    val items: List<AuthenticationSectionResponseData>
)
