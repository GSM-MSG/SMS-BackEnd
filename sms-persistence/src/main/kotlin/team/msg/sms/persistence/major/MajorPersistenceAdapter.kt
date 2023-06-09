package team.msg.sms.persistence.major

import org.springframework.stereotype.Component
import team.msg.sms.domain.major.model.Major
import team.msg.sms.domain.major.spi.MajorPort
import team.msg.sms.persistence.major.mapper.toDomain
import team.msg.sms.persistence.major.repository.MajorJpaRepository

@Component
class MajorPersistenceAdapter(
    private val majorJpaRepository: MajorJpaRepository
) : MajorPort {
    override fun queryAll(): List<Major> =
        majorJpaRepository.findAllByIsDefaultMajorTrue().map {
            it.toDomain()
        }
}