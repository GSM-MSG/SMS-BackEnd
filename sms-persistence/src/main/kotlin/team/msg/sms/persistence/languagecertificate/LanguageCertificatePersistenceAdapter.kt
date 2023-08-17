package team.msg.sms.persistence.languagecertificate

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.msg.sms.domain.languagecertificate.model.LanguageCertificate
import team.msg.sms.domain.languagecertificate.spi.LanguageCertificatePort
import team.msg.sms.domain.student.exception.StudentNotFoundException
import team.msg.sms.domain.student.model.Student
import team.msg.sms.domain.user.model.User
import team.msg.sms.persistence.languagecertificate.mapper.toDomain
import team.msg.sms.persistence.languagecertificate.mapper.toEntity
import team.msg.sms.persistence.languagecertificate.repository.LanguageCertificateJpaRepository
import team.msg.sms.persistence.student.mapper.toEntity
import team.msg.sms.persistence.student.repository.StudentJpaRepository
import team.msg.sms.persistence.user.mapper.toEntity
import java.util.*

@Component
class LanguageCertificatePersistenceAdapter(
    private val languageCertificateJpaRepository: LanguageCertificateJpaRepository,
    private val studentJpaRepository: StudentJpaRepository
) : LanguageCertificatePort {
    override fun saveAll(region: List<LanguageCertificate>, student: Student, user: User): List<LanguageCertificate> =
        languageCertificateJpaRepository.saveAll(region.map { it.toEntity(student.toEntity(user.toEntity())) })
            .map { it.toDomain() }

    override fun deleteAllByStudent(student: Student) {
        val student = studentJpaRepository.findByIdOrNull(student.id)
            ?: throw StudentNotFoundException
        languageCertificateJpaRepository.deleteAllByStudent(student)
    }

    override fun queryByStudentUuid(uuid: UUID): List<LanguageCertificate> =
        languageCertificateJpaRepository.findByStudentId(uuid).map { it.toDomain() }
}