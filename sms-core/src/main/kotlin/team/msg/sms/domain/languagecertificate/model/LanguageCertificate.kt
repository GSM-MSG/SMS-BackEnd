package team.msg.sms.domain.languagecertificate.model

import team.msg.sms.common.annotation.Aggregate

@Aggregate
class LanguageCertificate(
    val id: Long,
    val languageCertificateName: String,
    val studentId: Long
)