package team.msg.sms.domain.authentication

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import team.msg.sms.common.exception.InvalidUuidException
import team.msg.sms.domain.authentication.dto.res.CreateAuthenticationResponseData
import team.msg.sms.domain.authentication.dto.res.QueryAuthenticationDetailsResponseData
import team.msg.sms.domain.authentication.dto.res.RequestAuthenticationResponseData
import team.msg.sms.domain.authentication.req.CreateAuthenticationWebRequest
import team.msg.sms.domain.authentication.res.CreateAuthenticationWebResponse
import team.msg.sms.domain.authentication.res.QueryAuthenticationDetailsWebResponse
import team.msg.sms.domain.authentication.res.RequestAuthenticationWebResponse
import team.msg.sms.domain.authentication.usecase.CreateAuthenticationUseCase
import team.msg.sms.domain.authentication.usecase.DeleteAuthenticationUseCase
import team.msg.sms.domain.authentication.usecase.QueryAuthenticationDetailsUseCase
import team.msg.sms.domain.authentication.usecase.RequestAuthenticationUseCase
import java.util.*
import javax.validation.Valid
import javax.validation.constraints.Null

@RestController
@RequestMapping("/authentication")
class AuthenticationWebAdapter(
    private val createAuthenticationUseCase: CreateAuthenticationUseCase,
    private val queryAuthenticationDetailsUseCase: QueryAuthenticationDetailsUseCase,
    private val deleteAuthenticationUseCase: DeleteAuthenticationUseCase,
    private val requestAuthenticationUseCase: RequestAuthenticationUseCase
) {
    @PostMapping
    fun createAuthentication(@Valid @RequestBody request: CreateAuthenticationWebRequest): ResponseEntity<CreateAuthenticationWebResponse> =
        createAuthenticationUseCase.execute(request.toData())
            .let { ResponseEntity.status(HttpStatus.CREATED).body(it.toResponse()) }

    @DeleteMapping("/{uuid}")
    fun deleteAuthentication(@PathVariable uuid: String): ResponseEntity<Unit> {
        if(!isValidUUID(uuid)) throw InvalidUuidException
        deleteAuthenticationUseCase.execute(uuid)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/{uuid}")
    fun queryAuthenticationDetails(@PathVariable uuid: String): ResponseEntity<QueryAuthenticationDetailsWebResponse> {
        if (!isValidUUID(uuid)) throw InvalidUuidException
        return queryAuthenticationDetailsUseCase.execute(uuid)
            .let { ResponseEntity.ok(it.toResponse()) }
    }

    @PatchMapping("/{uuid}")
    fun requestAuthenticationDetails(@PathVariable uuid: String): ResponseEntity<RequestAuthenticationWebResponse> {
        if (!isValidUUID(uuid)) throw InvalidUuidException
        return requestAuthenticationUseCase.execute(uuid)
            .let { ResponseEntity.ok(it.toResponse()) }
    }

    private fun CreateAuthenticationResponseData.toResponse() = CreateAuthenticationWebResponse(
        id = id
    )

    private fun QueryAuthenticationDetailsResponseData.toResponse() = QueryAuthenticationDetailsWebResponse(
        id = id,
        title = title,
        content = content,
        activityImages = activityImages,
        lastModifiedDate = lastModifiedDate,
        score = score,
        activityStatus = activityStatus
    )

    private fun RequestAuthenticationResponseData.toResponse() = RequestAuthenticationWebResponse(
        id = id
    )

    private fun isValidUUID(uuid: String): Boolean {
        return try {
            UUID.fromString(uuid)
            true
        } catch (ex: IllegalArgumentException) {
            false
        }
    }
}