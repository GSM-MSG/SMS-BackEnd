package team.msg.sms.persistence.languagecertificate.mapper

import team.msg.sms.domain.languagecertificate.model.LanguageCertificate
import team.msg.sms.persistence.languagecertificate.entity.LanguageCertificateJpaEntity
import team.msg.sms.persistence.student.entity.StudentJpaEntity

fun LanguageCertificateJpaEntity.toDomain(): LanguageCertificate =
    LanguageCertificate(
        id = id,
        languageCertificateName = languageCertificateName,
        studentId = student.id
    )


fun LanguageCertificate.toEntity(
    student: StudentJpaEntity
): LanguageCertificateJpaEntity =
    LanguageCertificateJpaEntity(
        languageCertificateName = languageCertificateName,
        student = student
    )