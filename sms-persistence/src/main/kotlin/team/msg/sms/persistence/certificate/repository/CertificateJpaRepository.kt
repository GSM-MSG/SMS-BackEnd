package team.msg.sms.persistence.certificate.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import team.msg.sms.persistence.certificate.entity.CertificateJpaEntity
import team.msg.sms.persistence.student.entity.StudentJpaEntity
import java.util.*

@Repository
interface CertificateJpaRepository : CrudRepository<CertificateJpaEntity, Long> {
    fun findByStudentId(uuid: UUID): List<CertificateJpaEntity>
    fun deleteAllByStudent(student: StudentJpaEntity)
}