package team.msg.sms.domain.authentication.dto.res

import team.msg.sms.domain.file.dto.res.FileResponseData

data class QueryAuthenticationFormWebResponse(
    val files: List<FileResponseData>,
    val contents: List<AuthenticationAreaFormResponseData>
)
