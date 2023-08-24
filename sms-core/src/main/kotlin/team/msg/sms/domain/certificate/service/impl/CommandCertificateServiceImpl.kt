package team.msg.sms.domain.certificate.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.certificate.model.Certificate
import team.msg.sms.domain.certificate.service.CommandCertificateService
import team.msg.sms.domain.certificate.spi.CertificatePort
import team.msg.sms.domain.student.model.Student

@Service
class CommandCertificateServiceImpl(
    private val certificatePort: CertificatePort
) : CommandCertificateService {
    override fun saveAll(certificate: List<Certificate>): List<Certificate> =
        certificatePort.saveAll(certificate)

    override fun deleteAllByStudent(student: Student) =
        certificatePort.deleteAllByStudent(student)

    override fun deleteByCertificate(certificate: Certificate, student: Student) {
        certificatePort.deleteByCertificate(certificate, student)
    }
}