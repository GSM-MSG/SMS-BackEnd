package team.msg.sms.domain.auth

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.msg.sms.domain.auth.dto.SignInRequest
import team.msg.sms.domain.auth.dto.response.SignInResponse
import team.msg.sms.domain.auth.usecase.SignInUseCase
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
class AuthWebAdapter(
    private val signInUseCase: SignInUseCase
) {

    @PostMapping
    fun signIn(@Valid @RequestBody request: SignInRequest): ResponseEntity<SignInResponse> =
        signInUseCase.execute(request.toData())
            .let { ResponseEntity.ok(it) }

}