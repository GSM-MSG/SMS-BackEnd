package team.msg.sms.global.logger.filter

import org.slf4j.LoggerFactory
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class RequestLogFilter : OncePerRequestFilter() {
    private val log by lazy { LoggerFactory.getLogger(this.javaClass.simpleName) }
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        log.info("client ip = ${request.remoteAddr}")
        log.info("request method = ${request.method}")
        log.info("request url = ${request.requestURI}")
        log.info("client info = ${request.getHeader("User-Agent")}")
        log.info("client authentication token = ${request.getHeader("Authentication")}")
        filterChain.doFilter(request, response)
        log.info("response status = ${response.status} ---------------")
    }
}