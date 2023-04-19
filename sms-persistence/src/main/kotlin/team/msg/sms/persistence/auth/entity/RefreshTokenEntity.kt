package team.msg.sms.persistence.auth.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed
import java.util.UUID

@RedisHash(value = "refresh_token", timeToLive = 60L * 60 * 24 * 7)
data class RefreshTokenEntity(
    @Id
    @Indexed
    val token: String,
    @Indexed
    val userId: UUID
)
