package team.msg.sms.domain.certificate.service

import team.msg.sms.domain.certificate.model.Certificate
import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.user.model.User

interface CommandCertificateService {
    fun saveAll(certificate: List<Certificate>, student: Student, user: User): List<Certificate>
}