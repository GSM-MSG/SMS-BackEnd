package team.msg.sms.global.filter

import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import team.msg.sms.domain.auth.model.Role
import team.msg.sms.domain.student.usecase.ExistStudentUseCase
import team.msg.sms.domain.user.exception.UserNotFoundException
import team.msg.sms.domain.user.usecase.QueryUserByUserIdUseCase
import team.msg.sms.global.exception.InvalidUrlAccessException
import team.msg.sms.global.security.token.JwtParser
import team.msg.sms.global.security.token.JwtProperties
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtFilter(
    private val jwtParser: JwtParser,
    private val queryUserByUserIdUseCase: QueryUserByUserIdUseCase,
    private val existStudentUseCase: ExistStudentUseCase
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val cookieToken = resolvedCookieToken(request)
        if(cookieToken == null) {
            val token = resolveToken(request)

            token
                ?.let { SecurityContextHolder.getContext().authentication = jwtParser.getAuthentication(token) }

        } else {
            cookieToken
                .let {
                    val authentication: Authentication = jwtParser.getAuthentication(cookieToken)
                    SecurityContextHolder.getContext().authentication = authentication

                    val isAllowStudent = authentication.authorities.any { it.authority == Role.ROLE_STUDENT.name }
                    val isPostStudentEndpoint = request.requestURI == "/student" && request.method == "POST"

                    if (isAllowStudent && !isPostStudentEndpoint) {
                        val userId = UUID.fromString(jwtParser.getClaimsBody(cookieToken))
                        val user = queryUserByUserIdUseCase.execute(userId) ?: throw UserNotFoundException

                        if (!existStudentUseCase.execute(user)) {
                            throw InvalidUrlAccessException
                        }
                    }
                }
        }
        filterChain.doFilter(request, response)

    }

    private fun resolveToken(request: HttpServletRequest): String? =
        request.getHeader(JwtProperties.HEADER)
            ?.let { token -> jwtParser.parseToken(token) }

    private fun resolvedCookieToken(request: HttpServletRequest): String? =
        request.cookies
            ?.find { it.name == "accessToken" }?.value
}