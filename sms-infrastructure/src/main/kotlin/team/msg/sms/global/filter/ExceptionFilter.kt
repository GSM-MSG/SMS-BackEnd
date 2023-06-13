package team.msg.sms.global.filter

import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.web.filter.OncePerRequestFilter
import team.msg.sms.common.error.ErrorProperty
import team.msg.sms.common.error.SmsException
import team.msg.sms.global.error.ErrorResponse
import team.msg.sms.global.error.GlobalErrorCode
import team.msg.sms.global.exception.InternalServerErrorException
import java.nio.charset.StandardCharsets
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class ExceptionFilter(
    private val objectMapper: ObjectMapper
) : OncePerRequestFilter() {

    private val log by lazy { LoggerFactory.getLogger(this.javaClass.simpleName) }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        runCatching {
            filterChain.doFilter(request, response)
        }.onFailure { exception ->
            when (exception) {
                is SmsException -> {
                    log.error("request url = ${request.requestURI}")
                    log.error("exception message = ${exception.errorProperty.message()}")
                    log.error("response status = ${exception.errorProperty.status()} ---------------")
                    errorToJson(exception.errorProperty, response)
                }

                else -> {
                    when (val cause = exception.cause) {
                        is SmsException -> {
                            log.error("request url = ${request.requestURI}")
                            log.error("exception message = ${cause.errorProperty.message()}")
                            log.error("response status = ${cause.errorProperty.status()} ---------------")
                            errorToJson(cause.errorProperty, response)
                        }

                        else -> {
                            log.error("request url = ${request.requestURI}")
                            log.error("unknown exception message = ${exception.message} ---------------")
                            errorToJson(InternalServerErrorException.errorProperty, response)
                        }
                    }
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