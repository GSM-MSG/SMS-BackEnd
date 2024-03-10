package team.msg.sms.domain.authentication

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import team.msg.sms.common.exception.InvalidUuidException
import team.msg.sms.domain.authentication.dto.res.*
import team.msg.sms.domain.authentication.req.CreateAuthenticationWebRequest
import team.msg.sms.domain.authentication.res.CreateAuthenticationWebResponse
import team.msg.sms.domain.authentication.res.QueryAuthenticationDetailsWebResponse
import team.msg.sms.domain.authentication.res.QueryAuthenticationHistoriesWebResponse
import team.msg.sms.domain.authentication.res.RequestAuthenticationWebResponse
import team.msg.sms.domain.authentication.usecase.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/authentication")
class AuthenticationWebAdapter(
    private val createAuthenticationUseCase: CreateAuthenticationUseCase,
    private val queryAuthenticationDetailsUseCase: QueryAuthenticationDetailsUseCase,
    private val deleteAuthenticationUseCase: DeleteAuthenticationUseCase,
    private val requestAuthenticationUseCase: RequestAuthenticationUseCase,
    private val queryAuthenticationHistoriesUseCase: QueryAuthenticationHistoriesUseCase
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

    @GetMapping("/{uuid}/history")
    fun queryAuthenticationHistories(@PathVariable uuid: String): ResponseEntity<QueryAuthenticationHistoriesWebResponse> {
        if(!isValidUUID(uuid)) throw InvalidUuidException
        return queryAuthenticationHistoriesUseCase.execute(uuid)
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

    private fun QueryAuthenticationHistoriesResponseData.toResponse() = QueryAuthenticationHistoriesWebResponse(
        histories = histories
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