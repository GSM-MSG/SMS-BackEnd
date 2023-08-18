package team.msg.sms.persistence.languagecertificate

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.msg.sms.domain.languagecertificate.model.LanguageCertificate
import team.msg.sms.domain.languagecertificate.spi.LanguageCertificatePort
import team.msg.sms.domain.student.exception.StudentNotFoundException
import team.msg.sms.domain.student.model.Student
import team.msg.sms.persistence.languagecertificate.mapper.toDomain
import team.msg.sms.persistence.languagecertificate.mapper.toEntity
import team.msg.sms.persistence.languagecertificate.repository.LanguageCertificateJpaRepository
import team.msg.sms.persistence.student.repository.StudentJpaRepository
import java.util.*

@Component
class LanguageCertificatePersistenceAdapter(
    private val languageCertificateJpaRepository: LanguageCertificateJpaRepository,
    private val studentJpaRepository: StudentJpaRepository
) : LanguageCertificatePort {
    override fun saveAll(languageCertificates: List<LanguageCertificate>): List<LanguageCertificate> {
        val student = studentJpaRepository.findByIdOrNull(languageCertificates[0].studentId)
            ?: throw StudentNotFoundException
        return languageCertificateJpaRepository.saveAll(languageCertificates
            .map { it.toEntity(student) })
            .map { it.toDomain() }
    }

    override fun deleteAllByStudent(student: Student) {
        val student = studentJpaRepository.findByIdOrNull(student.id)
            ?: throw StudentNotFoundException
        languageCertificateJpaRepository.deleteAllByStudent(student)
    }

    override fun queryByStudentUuid(uuid: UUID): List<LanguageCertificate> =
        languageCertificateJpaRepository.findByStudentId(uuid).map { it.toDomain() }
}