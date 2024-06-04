package team.msg.sms.persistence.authentication


import org.springframework.stereotype.Component
import team.msg.sms.domain.authentication.model.GroupAuthenticationArea
import team.msg.sms.domain.authentication.spi.GroupAuthenticationAreaPort
import team.msg.sms.persistence.authentication.mapper.toDomain
import team.msg.sms.persistence.authentication.repository.GroupAuthenticationAreaRepository

@Component
class GroupAuthenticationAreaPersistenceAdapter(
    private val groupAuthenticationAreaRepository: GroupAuthenticationAreaRepository
) : GroupAuthenticationAreaPort {
    override fun queryGroupAuthenticationArea(): List<GroupAuthenticationArea> =
        groupAuthenticationAreaRepository.findAll().sortedBy { it.sort }.map { it.toDomain() }
}