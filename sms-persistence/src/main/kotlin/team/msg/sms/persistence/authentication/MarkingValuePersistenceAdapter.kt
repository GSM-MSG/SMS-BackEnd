package team.msg.sms.persistence.authentication

import org.springframework.stereotype.Component
import team.msg.sms.domain.authentication.model.MarkingValue
import team.msg.sms.domain.authentication.spi.MarkingValuePort
import team.msg.sms.persistence.authentication.mapper.toDomain
import team.msg.sms.persistence.authentication.mapper.toEntity
import team.msg.sms.persistence.authentication.repository.MarkingValueJpaRepository
import java.util.*

@Component
class MarkingValuePersistenceAdapter(
    private val markingValueJpaRepository: MarkingValueJpaRepository
) : MarkingValuePort {
    override fun saveAll(markingValueList: List<MarkingValue>): List<MarkingValue> =
        markingValueJpaRepository.saveAll(markingValueList.map { it.toEntity() }).map { it.toDomain() }

    override fun queryMarkingValueListByMarkingBoardId(markingBoardId: UUID): List<MarkingValue> =
        markingValueJpaRepository.findAllByMarkingBoardId(markingBoardId = markingBoardId).map { it.toDomain() }
}