package team.msg.sms.persistence.authentication

import org.springframework.stereotype.Component
import team.msg.sms.domain.authentication.model.SelectorSectionValue
import team.msg.sms.domain.authentication.spi.SelectorSectionValuePort
import team.msg.sms.persistence.authentication.mapper.toDomain
import team.msg.sms.persistence.authentication.mapper.toEntity
import team.msg.sms.persistence.authentication.repository.SelectorSectionValueRepository

@Component
class SelectorSectionValuePersistenceAdapter(
    private val selectorSectionValueRepository: SelectorSectionValueRepository
) : SelectorSectionValuePort {
    override fun querySelectorSectionValue(): List<SelectorSectionValue> =
        selectorSectionValueRepository.findAll().map { it.toDomain() }

    override fun saveAll(selectorSectionValueList: List<SelectorSectionValue>) {
        selectorSectionValueRepository.saveAll(
            selectorSectionValueList.map { it.toEntity() }
        )
    }
}