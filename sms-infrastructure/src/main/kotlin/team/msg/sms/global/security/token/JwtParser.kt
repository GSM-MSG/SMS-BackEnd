package team.msg.sms.global.security.token

import io.jsonwebtoken.*
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import team.msg.sms.domain.auth.model.Role
import team.msg.sms.global.error.InternalServerError
import team.msg.sms.global.security.SecurityProperties
import team.msg.sms.global.security.exception.ExpiredTokenException
import team.msg.sms.global.security.exception.InvalidRoleException
import team.msg.sms.global.security.exception.InvalidTokenException
import team.msg.sms.global.security.exception.UnexpectedTokenException
import team.msg.sms.global.security.principle.StudentDetailService


@Component
class JwtParser(
    private val securityProperties: SecurityProperties,
    private val studentDetailService: StudentDetailService
) {
    fun parseToken(token: String): String? =
        if(token.startsWith(JwtProperties.PREFIX)) token.substring(JwtProperties.PREFIX.length) else null
    fun getAuthentication(token: String): Authentication {
        val claims = getClaims(token)

        if(claims.header[Header.JWT_TYPE] != JwtProperties.ACCESS)
            throw InvalidTokenException

        val userDetails = getDetails(claims.body)

        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    fun getClaimsBody(token: String): String {
        val claims = getClaims(token)
        return claims.body.id
    }

    private fun getClaims(token: String): Jws<Claims> {
        return try {
            Jwts.parser()
                .setSigningKey(securityProperties.secretKey)
                .parseClaimsJws(token)
        } catch (e: Exception) {
            when(e) {
                is InvalidClaimException -> throw InvalidTokenException
                is ExpiredJwtException -> throw ExpiredTokenException
                is JwtException -> throw UnexpectedTokenException
                else -> throw InternalServerError
            }
        }
    }

    private fun getDetails(body: Claims): UserDetails {
        val role = body[JwtProperties.ROLE, String::class.java]

        return when(role) {
            Role.STUDENT.name -> studentDetailService.loadUserByUsername(body.id)
            else -> throw InvalidRoleException
        }
    }
}