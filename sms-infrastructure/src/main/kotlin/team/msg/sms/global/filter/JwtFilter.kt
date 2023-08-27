package team.msg.sms.global.filter

import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import team.msg.sms.global.security.token.JwtParser
import team.msg.sms.global.security.token.JwtProperties
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtFilter(
    private val jwtParser: JwtParser,
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = resolvedCookieToken(request) ?: resolveToken(request)

        token?.let {
            val authentication: Authentication = jwtParser.getAuthentication(it)
            SecurityContextHolder.getContext().authentication = authentication
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