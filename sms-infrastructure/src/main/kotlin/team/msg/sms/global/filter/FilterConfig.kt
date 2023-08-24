package team.msg.sms.global.filter

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.stereotype.Component
import team.msg.sms.domain.student.usecase.ExistStudentUseCase
import team.msg.sms.domain.user.usecase.QueryUserByUserIdUseCase
import team.msg.sms.global.logger.filter.RequestLogFilter
import team.msg.sms.global.security.token.JwtParser

@Component
class FilterConfig(
    private val jwtParser: JwtParser,
    private val objectMapper: ObjectMapper,
    private val existStudentUseCase: ExistStudentUseCase,
    private val queryUserByUserIdUseCase: QueryUserByUserIdUseCase
) : SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {
    override fun configure(builder: HttpSecurity) {
        builder.addFilterBefore(ExceptionFilter(objectMapper), UsernamePasswordAuthenticationFilter::class.java)
        builder.addFilterBefore(JwtFilter(jwtParser, queryUserByUserIdUseCase, existStudentUseCase), UsernamePasswordAuthenticationFilter::class.java)
        builder.addFilterBefore(RequestLogFilter(), UsernamePasswordAuthenticationFilter::class.java)
    }
}