package team.msg.sms.global.filter

import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import team.msg.sms.domain.auth.model.Role
import team.msg.sms.domain.student.exception.StudentNotFoundException
import team.msg.sms.domain.student.spi.QueryStudentPort
import team.msg.sms.domain.user.exception.UserNotFoundException
import team.msg.sms.domain.user.spi.QueryUserPort
import team.msg.sms.global.exception.InvalidUrlAccessException
import team.msg.sms.global.security.token.JwtParser
import java.util.UUID
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class CookieJwtFilter(
    private val jwtParser: JwtParser,
    private val studentPort: QueryStudentPort,
    private val userPort: QueryUserPort
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = resolvedToken(request)

        token
            ?.let {
                val authentication: Authentication = jwtParser.getAuthentication(token)
                SecurityContextHolder.getContext().authentication = authentication

                val isAllowStudent = authentication.authorities.any { it.authority == Role.ROLE_STUDENT.name }
                val isPostStudentEndpoint = request.requestURI == "/student" && request.method == "POST"

                if (isAllowStudent && !isPostStudentEndpoint) {
                    val userId = UUID.fromString(jwtParser.getClaimsBody(token))
                    val user = userPort.queryUserById(userId) ?: throw UserNotFoundException

                    if (!studentPort.existsStudentByUser(user)) {
                        throw InvalidUrlAccessException
                    }
                }
            }

        filterChain.doFilter(request, response)
    }

    private fun resolvedToken(request: HttpServletRequest): String? =
        request.cookies
            ?.find { it.name == "accessToken" }?.value
}