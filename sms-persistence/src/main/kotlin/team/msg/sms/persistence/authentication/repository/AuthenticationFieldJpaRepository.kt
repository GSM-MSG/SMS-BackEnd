package team.msg.sms.persistence.authentication.repository

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import team.msg.sms.persistence.authentication.entity.AuthenticationFieldJpaEntity
import java.util.UUID

@Repository
interface AuthenticationFieldJpaRepository: CrudRepository<AuthenticationFieldJpaEntity, UUID> {
    fun findAllByGroupIdOrderBySortAsc(groupId: UUID): List<AuthenticationFieldJpaEntity>
}