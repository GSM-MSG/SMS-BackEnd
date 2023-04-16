package team.msg.sms.global.filter

import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.web.filter.OncePerRequestFilter
import team.msg.sms.common.error.ErrorProperty
import team.msg.sms.common.error.SmsException
import team.msg.sms.global.error.ErrorResponse
import team.msg.sms.global.exception.InternalServerErrorException
import java.nio.charset.StandardCharsets
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class ExceptionFilter(
    private val objectMapper: ObjectMapper
) : OncePerRequestFilter() {

    private val log = LoggerFactory.getLogger(this.javaClass.simpleName)

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: SmsException) {
            log.error(e.message)
            errorToJson(e.errorProperty, response)
        } catch (e: Exception) {
            when (e.cause) {
                is SmsException -> {
                    errorToJson((e.cause as SmsException).errorProperty, response)
                    log.error(e.message)
                }

                else -> {
                    errorToJson(InternalServerErrorException.errorProperty, response)
                    log.error(InternalServerErrorException.message)
                }
            }
        }
    }

    private fun errorToJson(errorProperty: ErrorProperty, response: HttpServletResponse) {
        response.status = errorProperty.status()
        response.characterEncoding = StandardCharsets.UTF_8.name()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.writer.write(objectMapper.writeValueAsString(ErrorResponse.of(errorProperty)))
    }
}