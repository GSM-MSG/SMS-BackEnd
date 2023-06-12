package team.msg.sms.domain.languagecertificate.service

import team.msg.sms.domain.languagecertificate.model.LanguageCertificate
import team.msg.sms.domain.techstack.model.TechStack
import java.util.*

interface QueryLanguageCertificateService {
    fun getLanguageCertificateByStudentUuid(uuid: UUID): List<LanguageCertificate>
}