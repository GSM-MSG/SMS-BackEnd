package team.msg.sms.persistence.authentication.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import team.msg.sms.persistence.authentication.entity.UserFormValueJpaEntity
import java.util.UUID

@Repository
interface UserFormValueRepository : CrudRepository<UserFormValueJpaEntity, UUID> {
    fun findAllByAuthenticationFieldIdAndCreatedBy(fieldId: UUID, createdBy: UUID): List<UserFormValueJpaEntity>
}