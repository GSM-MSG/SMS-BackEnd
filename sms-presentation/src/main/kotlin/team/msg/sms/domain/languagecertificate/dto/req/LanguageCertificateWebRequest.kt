package team.msg.sms.domain.languagecertificate.dto.req

data class LanguageCertificateWebRequest(
    val languageCertificateName: String,
    val score: String
) {
    fun toData(): LanguageCertificateRequestData =
        LanguageCertificateRequestData(
            languageCertificateName = languageCertificateName,
            languageCertificateScore = score
        )
}
