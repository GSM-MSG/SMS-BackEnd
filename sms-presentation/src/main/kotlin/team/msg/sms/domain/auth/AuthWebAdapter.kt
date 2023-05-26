package team.msg.sms.domain.auth

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import team.msg.sms.domain.auth.dto.SignInRequest
import team.msg.sms.domain.auth.dto.response.ReIssueTokenResponse
import team.msg.sms.domain.auth.dto.response.SignInResponse
import team.msg.sms.domain.auth.usecase.LogoutUseCase
import team.msg.sms.domain.auth.usecase.ReIssueTokenUseCase
import team.msg.sms.domain.auth.usecase.SignInUseCase
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
class AuthWebAdapter(
    private val signInUseCase: SignInUseCase,
    private val reIssueTokenUseCase: ReIssueTokenUseCase,
    private val logoutUseCase: LogoutUseCase,
) {

    @PostMapping
    fun signIn(
        @Valid @RequestBody request: SignInRequest,
        httpServletResponse: HttpServletResponse
    ): ResponseEntity<SignInResponse> {
        val token: SignInResponse = signInUseCase.execute(request.toData())

        val accessCookie: Cookie = createCookie("accessToken", token.accessToken, 3600)
        val refreshCookie: Cookie = createCookie("refreshToken", token.refreshToken, 36000)

        httpServletResponse.addCookie(accessCookie)
        httpServletResponse.addCookie(refreshCookie)

        return ResponseEntity.ok(token)
    }

    @PatchMapping
    fun reIssueToken(@Valid @RequestHeader("Refresh-Token") header: String): ResponseEntity<ReIssueTokenResponse> =
        reIssueTokenUseCase.execute(header)
            .let { ResponseEntity.ok(it) }

    @DeleteMapping
    fun logout(
        @Valid @RequestHeader(name = "Refresh-Token", required = false) refreshToken: String?,
        httpServletResponse: HttpServletResponse
    ): ResponseEntity<Void> {
        if (refreshToken != null) logoutUseCase.execute(refreshToken)
        else {
            val accessCookie: Cookie = expiredCookie(httpServletResponse, "accessToken")
            val refreshCookie: Cookie = expiredCookie(httpServletResponse, "refreshToken")

            httpServletResponse.addCookie(accessCookie)
            httpServletResponse.addCookie(refreshCookie)
        }
        return ResponseEntity.ok().build()
    }

    private fun createCookie(value: String, token: String, maxAge: Int): Cookie {
        val cookie = Cookie(value, token)
        cookie.isHttpOnly = true
        cookie.maxAge = maxAge
        return cookie
    }

    private fun expiredCookie(httpServletResponse: HttpServletResponse, name: String): Cookie {
        val cookie: Cookie = Cookie(name, null)
        cookie.maxAge = 0
        return cookie
    }
}