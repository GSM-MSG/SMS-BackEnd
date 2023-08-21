package team.msg.sms.persistence.languagecertificate.mapper

import team.msg.sms.domain.languagecertificate.model.LanguageCertificate
import team.msg.sms.persistence.languagecertificate.entity.LanguageCertificateJpaEntity
import team.msg.sms.persistence.student.entity.StudentJpaEntity

fun LanguageCertificateJpaEntity.toDomain(): LanguageCertificate =
    LanguageCertificate(
        id = id,
        languageCertificateName = languageCertificateName,
        score = score,
        studentId = student.id
    )

fun LanguageCertificate.toDomainWithScore(): LanguageCertificate.LanguageCertificateScore =
    LanguageCertificate.LanguageCertificateScore(
        languageCertificateName = languageCertificateName,
        score = score,
    )


fun LanguageCertificate.toEntity(
    student: StudentJpaEntity
): LanguageCertificateJpaEntity =
    LanguageCertificateJpaEntity(
        id = id,
        languageCertificateName = languageCertificateName,
        score = score,
        student = student
    )