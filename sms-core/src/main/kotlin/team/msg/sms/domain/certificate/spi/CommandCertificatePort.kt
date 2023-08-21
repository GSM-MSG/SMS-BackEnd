package team.msg.sms.domain.certificate.spi

import team.msg.sms.domain.certificate.model.Certificate
import team.msg.sms.domain.student.model.Student

interface CommandCertificatePort {
    fun saveAll(certificate: List<Certificate>): List<Certificate>
    fun deleteAllByStudent(student: Student)
    fun deleteByCertificate(certificate: Certificate, student: Student)
}