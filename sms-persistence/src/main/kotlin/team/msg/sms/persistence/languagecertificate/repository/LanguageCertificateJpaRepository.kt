package team.msg.sms.persistence.languagecertificate.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import team.msg.sms.persistence.languagecertificate.entity.LanguageCertificateJpaEntity

@Repository
interface LanguageCertificateJpaRepository : CrudRepository<LanguageCertificateJpaEntity, Long> {
}