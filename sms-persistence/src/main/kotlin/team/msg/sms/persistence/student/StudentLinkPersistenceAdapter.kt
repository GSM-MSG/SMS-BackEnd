package team.msg.sms.persistence.student

import org.springframework.stereotype.Component
import team.msg.sms.domain.student.model.StudentLink
import team.msg.sms.domain.student.spi.StudentLinkPort
import team.msg.sms.persistence.student.mapper.toDomain
import team.msg.sms.persistence.student.mapper.toEntity
import team.msg.sms.persistence.student.redisRepository.StudentLinkRepository
import java.util.*

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

    override fun findStudentIdByToken(token: String): UUID? {
        return studentLinkRepository.findByToken(token)?.studentId
    }
}
