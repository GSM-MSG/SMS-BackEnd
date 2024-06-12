package team.msg.sms.domain.authentication.dto.res

import team.msg.sms.domain.file.dto.res.FileResponseData

data class QueryAuthenticationFormResponseData(
    val files: List<FileResponseData>,
    val content: List<AuthenticationAreaFormResponseData>
)
