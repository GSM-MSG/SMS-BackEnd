package team.msg.sms.persistence.student

import org.springframework.stereotype.Component
import team.msg.sms.domain.student.model.StudentLink
import team.msg.sms.domain.student.spi.StudentLinkPort
import team.msg.sms.persistence.student.mapper.toDomain
import team.msg.sms.persistence.student.mapper.toEntity
import team.msg.sms.persistence.student.repository.StudentJpaRepository
import team.msg.sms.persistence.student.repository.StudentLinkRepository

@Component
class StudentLinkPersistenceAdapter(
    private val studentLinkRepository: StudentLinkRepository
) : StudentLinkPort {
    override fun save(studentLink: StudentLink): StudentLink {
        return studentLinkRepository.save(
            studentLink.toEntity()
        ).toDomain()
    }

    override fun existsByToken(token: String): Boolean {
        return studentLinkRepository.existsByToken(token)
    }
}