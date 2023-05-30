package team.msg.sms.global.filter

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.stereotype.Component
import team.msg.sms.domain.student.spi.QueryStudentPort
import team.msg.sms.domain.user.spi.QueryUserPort
import team.msg.sms.global.security.token.JwtParser

@Component
class FilterConfig(
    private val jwtParser: JwtParser,
    private val objectMapper: ObjectMapper,
    private val studentPort: QueryStudentPort,
    private val userPort: QueryUserPort
) : SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {
    override fun configure(builder: HttpSecurity) {
        builder.addFilterBefore(ExceptionFilter(objectMapper), UsernamePasswordAuthenticationFilter::class.java)
        builder.addFilterBefore(CookieJwtFilter(jwtParser, studentPort, userPort), UsernamePasswordAuthenticationFilter::class.java)
        builder.addFilterBefore(JwtFilter(jwtParser), UsernamePasswordAuthenticationFilter::class.java)
    }
}