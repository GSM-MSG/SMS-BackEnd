package team.msg.sms.domain.languagecertificate.model

import team.msg.sms.common.annotation.Aggregate
import java.util.*

@Aggregate
class LanguageCertificate(
    val id: UUID,
    val languageCertificateName: String,
    val studentId: UUID
)