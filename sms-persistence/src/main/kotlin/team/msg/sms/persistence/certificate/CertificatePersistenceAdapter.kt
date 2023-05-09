package team.msg.sms.persistence.certificate

import org.springframework.stereotype.Component
import team.msg.sms.domain.certificate.model.Certificate
import team.msg.sms.domain.certificate.spi.CertificatePort
import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.user.model.User
import team.msg.sms.persistence.certificate.mapper.toDomain
import team.msg.sms.persistence.certificate.mapper.toEntity
import team.msg.sms.persistence.certificate.repository.CertificateJpaRepository
import team.msg.sms.persistence.student.mapper.toEntity
import team.msg.sms.persistence.user.mapper.toEntity

@Component
class CertificatePersistenceAdapter(
    private val certificateJpaRepository: CertificateJpaRepository
) : CertificatePort {
    override fun saveAll(certificate: List<Certificate>, student: Student, user: User): List<Certificate> =
        certificateJpaRepository.saveAll(certificate.map { it.toEntity(student.toEntity(user.toEntity())) })
            .map { it.toDomain() }
}