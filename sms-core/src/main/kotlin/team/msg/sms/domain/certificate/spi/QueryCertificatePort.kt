package team.msg.sms.domain.certificate.spi

import team.msg.sms.domain.certificate.model.Certificate
import java.util.UUID

interface QueryCertificatePort {
    fun queryByStudentUuid(uuid: UUID): List<Certificate>
}