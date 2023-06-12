package team.msg.sms.domain.languagecertificate.model

import team.msg.sms.common.annotation.Aggregate
import java.util.*

@Aggregate
class LanguageCertificate(
    val id: Long,
    val languageCertificateName: String,
    val score: String,
    val studentId: UUID
) {
    data class LanguageCertificateScore(
        val languageCertificateName: String,
        val score: String,
    )
}