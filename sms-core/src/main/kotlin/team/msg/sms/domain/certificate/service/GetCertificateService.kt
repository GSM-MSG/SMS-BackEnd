package team.msg.sms.domain.certificate.service

import team.msg.sms.domain.certificate.model.Certificate
import java.util.UUID

interface GetCertificateService {
    fun getCertificateByUuid(uuid: UUID): List<Certificate>
}