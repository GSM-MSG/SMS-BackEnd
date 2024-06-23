package team.msg.sms.persistence.user.repository

import org.springframework.data.repository.CrudRepository
import team.msg.sms.persistence.user.entity.UserFcmJpaEntity

interface UserFcmJpaRepository : CrudRepository<UserFcmJpaEntity, Long> {
}