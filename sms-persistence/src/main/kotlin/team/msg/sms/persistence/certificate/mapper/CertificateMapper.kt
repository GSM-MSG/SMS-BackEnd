package team.msg.sms.persistence.certificate.mapper

import team.msg.sms.domain.certificate.model.Certificate
import team.msg.sms.persistence.certificate.entity.CertificateJpaEntity
import team.msg.sms.persistence.student.entity.StudentJpaEntity

fun CertificateJpaEntity.toDomain(): Certificate =
    Certificate(
        id = id,
        certificateName = certificateName,
        studentId = student.id
    )

fun Certificate.toEntity(
    student: StudentJpaEntity
): CertificateJpaEntity =
    CertificateJpaEntity(
        id = id,
        certificateName = certificateName,
        student = student
    )