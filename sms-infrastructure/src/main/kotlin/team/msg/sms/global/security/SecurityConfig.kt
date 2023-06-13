package team.msg.sms.global.security

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.util.matcher.RequestMatcher
import org.springframework.web.cors.CorsUtils
import team.msg.sms.domain.student.spi.QueryStudentPort
import team.msg.sms.domain.user.spi.QueryUserPort
import team.msg.sms.global.filter.FilterConfig
import team.msg.sms.global.security.token.JwtParser

@Configuration
class SecurityConfig(
    private val jwtParser: JwtParser,
    private val objectMapper: ObjectMapper,
    private val studentPort: QueryStudentPort,
    private val userPort: QueryUserPort,
    private val customAuthenticationEntryPoint: CustomAuthenticationEntryPoint
) {

    @Bean
    protected fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .cors().and()
            .csrf().disable()
            .formLogin().disable()
            .httpBasic().disable()

        http
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http
            .authorizeRequests()
            .requestMatchers(RequestMatcher { request ->
                CorsUtils.isPreFlightRequest(request)
            }).permitAll()
            //healthCheck
            .antMatchers(HttpMethod.GET, "/health").permitAll()

            // auth
            .antMatchers(HttpMethod.POST, "/auth").permitAll()
            .antMatchers(HttpMethod.PATCH, "/auth").permitAll()
            .antMatchers(HttpMethod.DELETE, "/auth").authenticated()
            .antMatchers(HttpMethod.GET, "/auth/verity/access").authenticated()
            .antMatchers(HttpMethod.DELETE, "/auth/withdrawal").authenticated()

            .antMatchers(HttpMethod.GET, "/user/profile").permitAll()

            .antMatchers(HttpMethod.POST, "/student").authenticated()
            .antMatchers(HttpMethod.GET, "/student").permitAll()
            .antMatchers(HttpMethod.GET, "/student/{uuid}").hasAnyAuthority("ROLE_STUDENT", "ROLE_TEACHER")
            .antMatchers(HttpMethod.GET, "/student/anonymous/{uuid}").permitAll()

            .antMatchers(HttpMethod.POST, "/file").authenticated()
            .antMatchers(HttpMethod.POST, "/file/image").authenticated()

            .antMatchers(HttpMethod.GET, "/major/list").permitAll()

            .antMatchers(HttpMethod.GET, "/stack/list").permitAll()

            .anyRequest().authenticated()

        http
            .apply(FilterConfig(jwtParser, objectMapper, studentPort, userPort))

        http
            .exceptionHandling()
            .authenticationEntryPoint(customAuthenticationEntryPoint)
            .accessDeniedHandler(CustomAccessDeniedHandler(objectMapper))

        return http.build()
    }
}

