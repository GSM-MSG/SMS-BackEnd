package team.msg.sms.domain.certificate.service

import team.msg.sms.domain.certificate.model.Certificate
import team.msg.sms.domain.student.model.Student

interface CommandCertificateService {
    fun saveAll(certificate: List<Certificate>): List<Certificate>
    fun deleteAllByStudent(student: Student)
    fun deleteByCertificate(certificate: Certificate, student: Student)
}