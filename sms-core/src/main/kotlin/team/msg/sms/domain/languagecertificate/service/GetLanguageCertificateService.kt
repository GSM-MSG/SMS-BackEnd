package team.msg.sms.domain.languagecertificate.service

import team.msg.sms.domain.languagecertificate.model.LanguageCertificate
import java.util.*

interface GetLanguageCertificateService {
    fun getLanguageCertificateByStudentUuid(uuid: UUID): List<LanguageCertificate>
}