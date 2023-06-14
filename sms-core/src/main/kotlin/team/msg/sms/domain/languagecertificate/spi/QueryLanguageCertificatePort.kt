package team.msg.sms.domain.languagecertificate.spi

import team.msg.sms.domain.languagecertificate.model.LanguageCertificate
import java.util.*

interface QueryLanguageCertificatePort {
    fun queryByStudentUuid(uuid: UUID): List<LanguageCertificate>
}