package team.msg.sms.domain.auth

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import team.msg.sms.domain.auth.dto.SignInRequest
import team.msg.sms.domain.auth.dto.response.ReIssueTokenResponse
import team.msg.sms.domain.auth.dto.response.SignInResponse
import team.msg.sms.domain.auth.usecase.LogoutUseCase
import team.msg.sms.domain.auth.usecase.ReIssueTokenUseCase
import team.msg.sms.domain.auth.usecase.SignInUseCase
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
class AuthWebAdapter(
    private val signInUseCase: SignInUseCase,
    private val reIssueTokenUseCase: ReIssueTokenUseCase,
    private val logoutUseCase: LogoutUseCase
) {

    @PostMapping
    fun signIn(@Valid @RequestBody request: SignInRequest): ResponseEntity<SignInResponse> =
        signInUseCase.execute(request.toData())
            .let { ResponseEntity.ok(it) }

    @PatchMapping
    fun reIssueToken(@Valid @RequestHeader("Refresh-Token") header: String): ResponseEntity<ReIssueTokenResponse> =
        reIssueTokenUseCase.execute(header)
            .let { ResponseEntity.ok(it) }

    @DeleteMapping
    fun logout(@Valid @RequestHeader("Refresh-Token") header: String): ResponseEntity<Void> =
        logoutUseCase.execute(header)
            .let { ResponseEntity.ok().build() }
}