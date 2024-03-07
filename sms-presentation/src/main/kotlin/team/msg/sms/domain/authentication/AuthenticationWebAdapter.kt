package team.msg.sms.domain.authentication

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import team.msg.sms.common.exception.InvalidUuidException
import team.msg.sms.domain.authentication.dto.req.CreateAuthenticationWebRequest
import team.msg.sms.domain.authentication.dto.req.FindRequestedAuthenticationFiltersWebRequest
import team.msg.sms.domain.authentication.dto.res.*
import team.msg.sms.domain.authentication.usecase.CreateAuthenticationUseCase
import team.msg.sms.domain.authentication.usecase.QueryAuthenticationDetailsUseCase
import team.msg.sms.domain.authentication.usecase.QueryRequestedAuthenticationUseCase
import team.msg.sms.domain.authentication.usecase.RequestAuthenticationUseCase
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/authentication")
class AuthenticationWebAdapter(
    private val createAuthenticationUseCase: CreateAuthenticationUseCase,
    private val queryAuthenticationDetailsUseCase: QueryAuthenticationDetailsUseCase,
    private val requestAuthenticationUseCase: RequestAuthenticationUseCase,
    private val queryRequestedAuthenticationUseCase: QueryRequestedAuthenticationUseCase
) {
    @PostMapping
    fun createAuthentication(@Valid @RequestBody request: CreateAuthenticationWebRequest): ResponseEntity<CreateAuthenticationWebResponse> =
        createAuthenticationUseCase.execute(request.toData())
            .let { ResponseEntity.status(HttpStatus.CREATED).body(it.toResponse()) }

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

    @GetMapping("/teacher")
    fun queryRequestedAuthentication(
        @RequestParam(name = "page") page: Int,
        @RequestParam(name = "size") size: Int,
        filterRequestData: FindRequestedAuthenticationFiltersWebRequest
    ): ResponseEntity<QueryRequestedAuthenticationWebResponse> =
        queryRequestedAuthenticationUseCase.execute(page, size, filterRequestData.toData())
            .let { ResponseEntity.ok(it.toResponse()) }

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

    private fun QueryRequestedAuthenticationResponseData.toResponse() = QueryRequestedAuthenticationWebResponse(
        id = id,
        requestTime = requestTime,
        stuNum = stuNum,
        name = name,
        department = department,
        title = title
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