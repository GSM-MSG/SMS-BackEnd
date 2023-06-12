package team.msg.sms.domain.languagecertificate.service.impl

import org.springframework.stereotype.Service
import team.msg.sms.domain.languagecertificate.model.LanguageCertificate
import team.msg.sms.domain.languagecertificate.service.QueryLanguageCertificateService
import team.msg.sms.domain.languagecertificate.spi.LanguageCertificatePort
import team.msg.sms.domain.techstack.model.TechStack
import java.util.*

@Service
class QueryLanguageCertificateServiceImpl(
    private val languageCertificatePort: LanguageCertificatePort
) : QueryLanguageCertificateService {
    override fun getLanguageCertificateByStudentUuid(uuid: UUID): List<LanguageCertificate> =
        languageCertificatePort.findByStudentUuid(uuid)
}