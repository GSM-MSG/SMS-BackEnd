package team.msg.sms.domain.certificate.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.certificate.model.Certificate
import team.msg.sms.domain.certificate.service.CommandCertificateService
import team.msg.sms.domain.certificate.spi.CertificatePort
import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.user.model.User

@Service
class CommandCertificateServiceImpl(
    private val certificatePort: CertificatePort
) : CommandCertificateService {
    override fun saveAll(certificate: List<Certificate>, student: Student, user: User): List<Certificate> =
        certificatePort.saveAll(certificate, student, user)

}