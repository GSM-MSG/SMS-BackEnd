package team.msg.sms.global.security.token

import io.jsonwebtoken.Header
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import team.msg.sms.domain.auth.dto.response.TokenResponse
import team.msg.sms.domain.auth.model.Role
import team.msg.sms.domain.auth.spi.JwtPort
import team.msg.sms.global.security.SecurityProperties
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.util.*

@Component
class GenerateJwtAdapter(
    private val securityProperties: SecurityProperties,
) : JwtPort {
    override fun receiveToken(userId: UUID, role: Role) = TokenResponse(
        accessToken = generatedAccessToken(userId, role),
        refreshToken = generatedRefreshToken(userId, role),
        accessTokenExp = LocalDateTime.now().withNano(0).plusSeconds(securityProperties.accessExp.toLong()),
        refreshTokenExp = LocalDateTime.now().withNano(0).plusSeconds(securityProperties.refreshExp.toLong())
    )

    private fun generatedAccessToken(userId: UUID, role: Role) =
        Jwts.builder()
            .signWith(SignatureAlgorithm.HS512, securityProperties.secretKey)
            .setHeaderParam(Header.JWT_TYPE, JwtProperties.ACCESS)
            .setId(userId.toString())
            .claim(JwtProperties.ROLE, role.name)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + securityProperties.accessExp * 1000))
            .compact()

    private fun generatedRefreshToken(userId: UUID, role: Role) =
        Jwts.builder()
            .signWith(SignatureAlgorithm.HS512, securityProperties.secretKey)
            .setHeaderParam(Header.JWT_TYPE, JwtProperties.REFRESH)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + securityProperties.refreshExp * 1000))
            .compact()
}