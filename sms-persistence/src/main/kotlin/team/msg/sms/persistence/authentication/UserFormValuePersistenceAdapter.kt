package team.msg.sms.persistence.authentication

import org.springframework.stereotype.Component
import team.msg.sms.domain.authentication.model.UserFormValue
import team.msg.sms.domain.authentication.spi.UserFormValuePort
import team.msg.sms.persistence.authentication.mapper.toDomain
import team.msg.sms.persistence.authentication.mapper.toEntity
import team.msg.sms.persistence.authentication.repository.UserFormValueRepository

@Component
class UserFormValuePersistenceAdapter(
    private val userFormValueRepository: UserFormValueRepository
) : UserFormValuePort {
    override fun saveAll(userFormValueList: List<UserFormValue>): List<UserFormValue> {
        return userFormValueRepository.saveAll(userFormValueList.map { it.toEntity() })
            .map { it.toDomain() }
    }
}