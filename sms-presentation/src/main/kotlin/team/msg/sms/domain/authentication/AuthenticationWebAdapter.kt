package team.msg.sms.domain.authentication

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import team.msg.sms.common.exception.InvalidUuidException
import team.msg.sms.domain.authentication.dto.res.*
import team.msg.sms.domain.authentication.res.QueryAuthenticationHistoriesWebResponse
import team.msg.sms.domain.authentication.usecase.*
import team.msg.sms.domain.authentication.dto.req.CreateAuthenticationWebRequest
import team.msg.sms.domain.authentication.dto.req.FindRequestedAuthenticationFiltersWebRequest
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
    private val queryRequestedAuthenticationUseCase: QueryRequestedAuthenticationUseCase,
    private val deleteAuthenticationUseCase: DeleteAuthenticationUseCase,
    private val requestAuthenticationUseCase: RequestAuthenticationUseCase,
    private val queryAuthenticationHistoriesUseCase: QueryAuthenticationHistoriesUseCase,
    private val queryMyAuthenticationUseCase: QueryMyAuthenticationUseCase,
    private val queryStudentAuthenticationUseCase: QueryStudentAuthenticationUseCase
) {
    @PostMapping
    fun createAuthentication(@Valid @RequestBody request: CreateAuthenticationWebRequest): ResponseEntity<CreateAuthenticationWebResponse> =
        createAuthenticationUseCase.execute(request.toData())
            .let { ResponseEntity.status(HttpStatus.CREATED).body(it.toResponse()) }

    @GetMapping("/my")
    fun queryMyAuthentication(): ResponseEntity<QueryMyAuthenticationListWebResponse> {
        return queryMyAuthenticationUseCase.execute()
            .let { ResponseEntity.ok(it.toResponse()) }
    }

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

    @GetMapping("/teacher")
    fun queryRequestedAuthentication(
        @RequestParam(name = "page") page: Int,
        @RequestParam(name = "size") size: Int,
        filterRequestData: FindRequestedAuthenticationFiltersWebRequest
    ): ResponseEntity<QueryRequestedAuthenticationListWebResponse> =
        queryRequestedAuthenticationUseCase.execute(page, size, filterRequestData.toData())
            .let { ResponseEntity.ok(it.toResponse()) }

    @GetMapping("/student/{student_id}")
    fun queryStudentAuthentication(@PathVariable(name = "student_id") studentUuid: String){
        queryStudentAuthenticationUseCase.execute(studentUuid)
            .let { ResponseEntity.ok(it.toResponse()) }
    }

    private fun QueryStudentAuthenticationListResponseData.toResponse() = QueryStudentAuthenticationListWebResponse(
        activities = activities
    )

    private fun QueryMyAuthenticationListResponseData.toResponse() = QueryMyAuthenticationListWebResponse(
        activities = activities
    )

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

    private fun QueryRequestedAuthenticationListResponseData.toResponse() = QueryRequestedAuthenticationListWebResponse(
        content = content.map { it.toResponse() },
        page = page,
        contentSize = contentSize,
        totalSize = totalSize,
        last = last
    )

    private fun RequestedAuthenticationResponseData.toResponse() = RequestedAuthenticationWebResponse(
        id = id,
        requestedTime = requestedTime,
        title = title,
        stuNum = stuNum,
        name = name,
        department = department
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