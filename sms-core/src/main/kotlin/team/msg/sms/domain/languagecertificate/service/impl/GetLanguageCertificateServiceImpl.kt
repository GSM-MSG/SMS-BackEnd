package team.msg.sms.domain.languagecertificate.service.impl

import org.springframework.stereotype.Service
import team.msg.sms.domain.languagecertificate.model.LanguageCertificate
import team.msg.sms.domain.languagecertificate.service.GetLanguageCertificateService
import team.msg.sms.domain.languagecertificate.spi.LanguageCertificatePort
import java.util.*

@Service
class GetLanguageCertificateServiceImpl(
    private val languageCertificatePort: LanguageCertificatePort
) : GetLanguageCertificateService {
    override fun getLanguageCertificateByStudentUuid(uuid: UUID): List<LanguageCertificate> =
        languageCertificatePort.queryByStudentUuid(uuid)
}