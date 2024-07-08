package team.msg.sms.domain.authentication.dto.req

import team.msg.sms.domain.file.dto.req.CreateFileRequestData

data class CreateAuthenticationFormRequestData(
    val title: String,
    val version: String,
    val files: List<CreateFileRequestData>,
    val formData: List<CreateAuthenticationAreaFormRequestData>
)
