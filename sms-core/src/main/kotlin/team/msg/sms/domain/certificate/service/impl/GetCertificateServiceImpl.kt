package team.msg.sms.domain.certificate.service.impl

import team.msg.sms.domain.certificate.model.Certificate
import team.msg.sms.domain.certificate.service.GetCertificateService
import team.msg.sms.domain.certificate.spi.CertificatePort
import java.util.*

class GetCertificateServiceImpl(
    private val certificatePort: CertificatePort
) : GetCertificateService {
    override fun getCertificateByUuid(uuid: UUID): List<Certificate> =
        certificatePort.findByStudentUuid(uuid)
}