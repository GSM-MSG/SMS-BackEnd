package team.msg.sms.domain.auth

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.msg.sms.domain.auth.dto.SignInRequest
import team.msg.sms.domain.auth.dto.response.SignInResponse
import team.msg.sms.domain.auth.usecase.ReIssueTokenUseCase
import team.msg.sms.domain.auth.usecase.SignInUseCase
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
class AuthWebAdapter(
    private val signInUseCase: SignInUseCase,
    private val reIssueTokenUseCase: ReIssueTokenUseCase
) {

    @PostMapping
    fun signIn(@Valid @RequestBody request: SignInRequest): ResponseEntity<SignInResponse> =
        signInUseCase.execute(request.toData())
            .let { ResponseEntity.ok(it) }

    @PatchMapping
    fun reIssueToken(@Valid @RequestHeader("Refresh-Token") header: String) =
        reIssueTokenUseCase.execute(header)
            .let { ResponseEntity.ok(it) }

}