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
import team.msg.sms.global.filter.FilterConfig
import team.msg.sms.global.security.token.JwtParser

@Configuration
class SecurityConfig(
    private val jwtParser: JwtParser,
    private val objectMapper: ObjectMapper
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

            // auth
            .antMatchers(HttpMethod.POST, "/auth").permitAll()
            .antMatchers(HttpMethod.PATCH, "/auth").permitAll()
            .antMatchers(HttpMethod.DELETE, "/auth").authenticated()

            .antMatchers(HttpMethod.POST, "/student").authenticated()

            .antMatchers(HttpMethod.POST, "/file").authenticated()
            .antMatchers(HttpMethod.POST, "/file/image").authenticated()

            .antMatchers(HttpMethod.GET,"/major/list").authenticated()

            .anyRequest().authenticated()

        http
            .apply(FilterConfig(jwtParser, objectMapper))


        return http.build()
    }
}