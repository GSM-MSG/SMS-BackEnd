package team.msg.sms.global.security

import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import team.msg.sms.global.error.ErrorResponse
import team.msg.sms.global.security.exception.InvalidRoleException
import team.msg.sms.global.security.exception.error.SecurityErrorCode
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class CustomAccessDeniedHandler(
    private val objectMapper: ObjectMapper
) : AccessDeniedHandler {

    private val log = LoggerFactory.getLogger(this.javaClass.simpleName)

    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException
    ) {
        log.error("==========Access Denied==========")
        val errorCode = SecurityErrorCode.FORBIDDEN
        val responseString = objectMapper.writeValueAsString(ErrorResponse(errorCode.status(), errorCode.message()))
        response.characterEncoding = "UTF-8"
        response.status = errorCode.status()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.writer.write(responseString)
    }
}