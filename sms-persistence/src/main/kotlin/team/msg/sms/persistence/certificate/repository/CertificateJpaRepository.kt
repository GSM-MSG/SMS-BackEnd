package team.msg.sms.persistence.certificate.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import team.msg.sms.persistence.certificate.entity.CertificateJpaEntity
import java.util.*

@Repository
interface CertificateJpaRepository : CrudRepository<CertificateJpaEntity, Long> {
    fun findByStudentId(uuid: UUID): List<CertificateJpaEntity>
}