package team.msg.sms.domain.languagecertificate.service.impl

import team.msg.sms.common.annotation.Service
import team.msg.sms.domain.languagecertificate.model.LanguageCertificate
import team.msg.sms.domain.languagecertificate.service.CommandLanguageCertificateService
import team.msg.sms.domain.languagecertificate.spi.LanguageCertificatePort
import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.user.model.User

@Service
class CommandLanguageCertificateServiceImpl(
    val languageCertificatePort: LanguageCertificatePort
) : CommandLanguageCertificateService {
    override fun saveAll(
        languageCertificate: List<LanguageCertificate>,
        student: Student,
        user: User
    ): List<LanguageCertificate> =
        languageCertificatePort.saveAll(languageCertificate, student, user)

    override fun deleteAllByStudent(student: Student) =
        languageCertificatePort.deleteAllByStudent(student)

    override fun deleteByLanguageCertificate(languageCertificate: LanguageCertificate, student: Student) =
        languageCertificatePort.deleteByLanguageCertificate(languageCertificate, student)
}