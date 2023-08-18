package team.msg.sms.domain.languagecertificate.service

import team.msg.sms.domain.languagecertificate.model.LanguageCertificate
import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.user.model.User

interface CommandLanguageCertificateService {
    fun saveAll(languageCertificate: List<LanguageCertificate>): List<LanguageCertificate>
    fun deleteAllByStudent(student: Student)
}