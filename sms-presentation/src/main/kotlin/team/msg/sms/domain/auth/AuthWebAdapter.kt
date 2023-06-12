package team.msg.sms.domain.auth

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import team.msg.sms.domain.auth.dto.SignInRequest
import team.msg.sms.domain.auth.dto.VerifyAccessResponse
import team.msg.sms.domain.auth.dto.response.ReIssueTokenResponse
import team.msg.sms.domain.auth.dto.response.SignInResponse
import team.msg.sms.domain.auth.usecase.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
class AuthWebAdapter(
    private val signInUseCase: SignInUseCase,
    private val reIssueTokenUseCase: ReIssueTokenUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val verifyAccessUseCase: VerifyAccessUseCase,
    private val withdrawalUseCase: WithdrawalUseCase
) {

    @PostMapping
    fun signIn(
        @Valid @RequestBody request: SignInRequest,
        httpServletResponse: HttpServletResponse
    ): ResponseEntity<SignInResponse> {
        val token: SignInResponse = signInUseCase.execute(request.toData())

        createCookie(httpServletResponse, "accessToken", token.accessToken, 3600)
        createCookie(httpServletResponse, "refreshToken", token.refreshToken, 36000)

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
            expiredCookie(httpServletResponse, "accessToken")
            expiredCookie(httpServletResponse, "refreshToken")
        }
        return ResponseEntity.ok().build()
    }

    @GetMapping("/verify/access")
    fun verifyAccess(): ResponseEntity<VerifyAccessResponse> =
        verifyAccessUseCase.execute()
            .let { ResponseEntity.ok(it.toResponse()) }

    @DeleteMapping("/withdrawal")
    fun withdrawal(): ResponseEntity<Void> =
        withdrawalUseCase.execute()
            .run { ResponseEntity.ok().build() }

    private fun createCookie(httpServletResponse: HttpServletResponse, value: String, token: String, maxAge: Int) {
        val cookie = Cookie(value, token)
        cookie.isHttpOnly = true
        cookie.domain = "port-0-sms-backend-otjl2cli2nay6y.sel4.cloudtype.app"
        cookie.maxAge = maxAge
        httpServletResponse.addCookie(cookie)
    }

    private fun expiredCookie(httpServletResponse: HttpServletResponse, name: String) {
        val cookie: Cookie = Cookie(name, null)
        cookie.maxAge = 0
        httpServletResponse.addCookie(cookie)
    }

    private fun Boolean.toResponse(): VerifyAccessResponse =
        VerifyAccessResponse(
            isExist = this
        )
}