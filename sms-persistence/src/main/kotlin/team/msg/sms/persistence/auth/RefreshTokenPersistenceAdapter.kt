package team.msg.sms.persistence.auth

import org.springframework.stereotype.Component
import team.msg.sms.domain.auth.model.RefreshToken
import team.msg.sms.domain.auth.spi.RefreshTokenPort
import team.msg.sms.persistence.auth.mapper.toDomain
import team.msg.sms.persistence.auth.mapper.toEntity
import team.msg.sms.persistence.auth.repository.RefreshTokenRepository

@Component
class RefreshTokenPersistenceAdapter(
    private val refreshTokenRepository: RefreshTokenRepository,
) : RefreshTokenPort {
    override fun saveRefreshToken(refreshToken: RefreshToken): RefreshToken =
        refreshTokenRepository
            .save(refreshToken.toEntity())
            .toDomain()

    override fun queryRefreshTokenByToken(token: String): RefreshToken? =
        refreshTokenRepository.findByToken(token)?.toDomain()
}