package team.msg.sms.domain.languagecertificate.spi

import team.msg.sms.domain.languagecertificate.model.LanguageCertificate
import team.msg.sms.domain.student.model.Student

interface CommandLanguageCertificatePort {
    fun saveAll(languageCertificates: List<LanguageCertificate>): List<LanguageCertificate>
    fun deleteAllByStudent(student: Student)
    fun deleteByLanguageCertificate(languageCertificate: LanguageCertificate, student: Student)
}