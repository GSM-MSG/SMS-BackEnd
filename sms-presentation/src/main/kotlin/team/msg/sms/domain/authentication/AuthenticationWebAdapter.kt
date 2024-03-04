package team.msg.sms.domain.authentication

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.msg.sms.domain.authentication.dto.res.CreateAuthenticationResponseData
import team.msg.sms.domain.authentication.req.CreateAuthenticationWebRequest
import team.msg.sms.domain.authentication.res.CreateAuthenticationWebResponse
import team.msg.sms.domain.authentication.usecase.CreateAuthenticationUseCase
import javax.validation.Valid

@RestController
@RequestMapping("/authentication")
class AuthenticationWebAdapter(
    private val createAuthenticationUseCase: CreateAuthenticationUseCase
) {
    @PostMapping
    fun createAuthentication(@Valid @RequestBody request: CreateAuthenticationWebRequest): ResponseEntity<CreateAuthenticationWebResponse> =
        createAuthenticationUseCase.execute(request.toData())
            .let { ResponseEntity.status(HttpStatus.CREATED).body(it.toResponse()) }

    private fun CreateAuthenticationResponseData.toResponse() = CreateAuthenticationWebResponse(
        id = id
    )
}