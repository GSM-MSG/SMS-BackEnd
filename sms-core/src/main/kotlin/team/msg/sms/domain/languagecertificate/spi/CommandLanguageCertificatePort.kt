package team.msg.sms.domain.languagecertificate.spi

import team.msg.sms.domain.languagecertificate.model.LanguageCertificate
import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.user.model.User

interface CommandLanguageCertificatePort {
    fun saveAll(region: List<LanguageCertificate>, student: Student, user: User): List<LanguageCertificate>
    fun deleteAllByStudent(student: Student)
}