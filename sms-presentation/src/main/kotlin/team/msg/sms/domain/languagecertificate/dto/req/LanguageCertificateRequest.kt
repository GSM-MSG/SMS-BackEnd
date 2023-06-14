package team.msg.sms.domain.languagecertificate.dto.req

import team.msg.sms.domain.languagecertificate.dto.LanguageCertificateData

data class LanguageCertificateRequest(
    val languageCertificateName: String,
    val score: String
) {
    fun toData(): LanguageCertificateData =
        LanguageCertificateData(
            languageCertificateName = languageCertificateName,
            languageCertificateScore = score
        )
}
