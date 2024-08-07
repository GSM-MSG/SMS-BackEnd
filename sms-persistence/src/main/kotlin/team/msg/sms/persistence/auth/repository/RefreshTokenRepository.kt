package team.msg.sms.persistence.auth.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import team.msg.sms.persistence.auth.entity.RefreshTokenEntity

@Repository
interface RefreshTokenRepository : CrudRepository<RefreshTokenEntity, Long> {
    fun findByToken(token: String): RefreshTokenEntity?
}