package team.msg.sms.persistence.authentication


import org.springframework.stereotype.Component
import team.msg.sms.domain.authentication.model.GroupAuthenticationArea
import team.msg.sms.domain.authentication.spi.GroupAuthenticationAreaPort
import team.msg.sms.persistence.authentication.mapper.toDomain
import team.msg.sms.persistence.authentication.mapper.toEntity
import team.msg.sms.persistence.authentication.repository.GroupAuthenticationAreaRepository
import java.util.UUID

@Component
class GroupAuthenticationAreaPersistenceAdapter(
    private val groupAuthenticationAreaRepository: GroupAuthenticationAreaRepository
) : GroupAuthenticationAreaPort {
    override fun queryGroupAuthenticationAreaByAuthenticationFormId(authenticationFormId: UUID): List<GroupAuthenticationArea> =
        groupAuthenticationAreaRepository.findByAuthenticationFormId(authenticationFormId).sortedBy { it.sort }.map { it.toDomain() }

    override fun save(groupAuthenticationArea: GroupAuthenticationArea): GroupAuthenticationArea =
        groupAuthenticationAreaRepository.save(groupAuthenticationArea.toEntity()).toDomain()
}