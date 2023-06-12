package team.msg.sms.domain.languagecertificate.spi

import team.msg.sms.domain.languagecertificate.model.LanguageCertificate
import java.util.*

interface QueryLanguageCertificatePort {
    fun findByStudentUuid(uuid: UUID): List<LanguageCertificate>
}