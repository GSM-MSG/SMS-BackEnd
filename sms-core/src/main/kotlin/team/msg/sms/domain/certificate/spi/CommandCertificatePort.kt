package team.msg.sms.domain.certificate.spi

import team.msg.sms.domain.certificate.model.Certificate
import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.user.model.User

interface CommandCertificatePort {
    fun saveAll(certificate: List<Certificate>, student: Student, user: User): List<Certificate>
    fun deleteAllByStudent(student: Student)
}