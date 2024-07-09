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
    private val objectMapper: ObjectMapper,
    private val customAuthenticationEntryPoint: CustomAuthenticationEntryPoint
) {

    companion object {
        const val STUDENT = "ROLE_STUDENT" // 학생
        const val TEACHER = "ROLE_TEACHER" // 선생님
        const val PRINCIPAL = "ROLE_PRINCIPAL" // 교장선생님
        const val DEPUTY_PRINCIPAL = "ROLE_DEPUTY_PRINCIPAL" // 교감선생님
        const val DIRECTOR = "ROLE_DIRECTOR" // 부장선생님
        const val HOMEROOM = "ROLE_HOMEROOM" // 담임선생님
    }

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
            // Health
            .antMatchers(HttpMethod.GET, "/health").permitAll()

            // Auth
            .antMatchers(HttpMethod.GET, "/auth/verity/access").authenticated()
            .antMatchers(HttpMethod.POST, "/auth").permitAll()
            .antMatchers(HttpMethod.PATCH, "/auth").permitAll()
            .antMatchers(HttpMethod.DELETE, "/auth").authenticated()
            .antMatchers(HttpMethod.DELETE, "/auth/withdrawal").authenticated()

            // Student
            .antMatchers(HttpMethod.GET, "/student/link").permitAll()
            .antMatchers(HttpMethod.GET, "/student/{uuid}").hasAnyAuthority(STUDENT, TEACHER)
            .antMatchers(HttpMethod.GET, "/student/anonymous/{uuid}").permitAll()
            .antMatchers(HttpMethod.GET, "/student/teacher/{uuid}").hasAuthority(TEACHER)
            .antMatchers(HttpMethod.GET, "/student").permitAll()
            .antMatchers(HttpMethod.PUT, "/student/pdf").hasAuthority(STUDENT)
            .antMatchers(HttpMethod.PUT, "/student").hasAuthority(STUDENT)
            .antMatchers(HttpMethod.POST, "/student").hasAuthority(STUDENT)
            .antMatchers(HttpMethod.POST, "/student/link").hasAuthority(TEACHER)

            // Teacher
            .antMatchers(HttpMethod.POST, "/teacher/common").hasAuthority(TEACHER)
            .antMatchers(HttpMethod.POST, "/teacher/director").hasAuthority(TEACHER)
            .antMatchers(HttpMethod.POST, "/teacher/homeroom").hasAuthority(TEACHER)
            .antMatchers(HttpMethod.POST, "/teacher/principal").hasAuthority(TEACHER)
            .antMatchers(HttpMethod.POST, "/teacher/deputy-principal").hasAuthority(TEACHER)

            // File
            .antMatchers(HttpMethod.POST, "/file").authenticated()
            .antMatchers(HttpMethod.POST, "/file/image").authenticated()

            // Major
            .antMatchers(HttpMethod.GET, "/major/list").permitAll()

            // Stack
            .antMatchers(HttpMethod.GET, "/stack/list").permitAll()

            // User
            .antMatchers(HttpMethod.GET, "/user/profile/img").permitAll()
            .antMatchers(HttpMethod.GET, "/user/profile").hasAuthority(STUDENT)

            // Authentication
            .antMatchers(HttpMethod.GET, "/authentication/student/{student_uuid}").hasAuthority(TEACHER)
            .antMatchers(HttpMethod.GET, "/authentication/teacher").hasAuthority(TEACHER)
            .antMatchers(HttpMethod.GET, "/authentication/teacher/{uuid}").hasAuthority(TEACHER)
            .antMatchers(HttpMethod.GET, "/authentication/{uuid}/history").hasAnyAuthority(STUDENT, TEACHER)
            .antMatchers(HttpMethod.GET, "/authentication/my").hasAuthority(STUDENT)
            .antMatchers(HttpMethod.GET, "/authentication/{uuid}").hasAuthority(STUDENT)
            .antMatchers(HttpMethod.GET, "/").hasAnyAuthority(STUDENT, TEACHER)
            .antMatchers(HttpMethod.GET, "/authentication/form").hasAnyAuthority(STUDENT, TEACHER)
            .antMatchers(HttpMethod.GET, "/authentication/{uuid}/form").hasAuthority(TEACHER)
            .antMatchers(HttpMethod.GET, "/authentication").hasAuthority(TEACHER)
            .antMatchers(HttpMethod.PUT, "/authentication/{uuid}").hasAuthority(STUDENT)
            .antMatchers(HttpMethod.POST, "/authentication").hasAuthority(STUDENT)
            .antMatchers(HttpMethod.POST, "/authentication/submit").hasAuthority(STUDENT)
            .antMatchers(HttpMethod.POST, "/authentication/create").hasAuthority(TEACHER)
            .antMatchers(HttpMethod.POST, "/authentication/grading/{markingBoardId}").hasAuthority(TEACHER)
            .antMatchers(HttpMethod.PATCH, "/authentication/teacher/{uuid}/approve").hasAuthority(TEACHER)
            .antMatchers(HttpMethod.PATCH, "/authentication/teacher/{uuid}/reject").hasAuthority(TEACHER)
            .antMatchers(HttpMethod.PATCH, "/authentication/{uuid}").hasAuthority(STUDENT)
            .antMatchers(HttpMethod.DELETE, "/authentication/{uuid}").hasAuthority(STUDENT)

            .anyRequest().denyAll()

        http
            .apply(FilterConfig(jwtParser, objectMapper))

        http
            .exceptionHandling()
            .authenticationEntryPoint(customAuthenticationEntryPoint)
            .accessDeniedHandler(CustomAccessDeniedHandler(objectMapper))

        return http.build()
    }
}