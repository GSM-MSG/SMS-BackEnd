package team.msg.sms.domain.student

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import team.msg.sms.domain.student.dto.FindAllFiltersWebRequest
import team.msg.sms.domain.student.dto.SignUpWebRequest
import team.msg.sms.domain.student.dto.response.StudentInfoListResponse
import team.msg.sms.domain.student.usecase.FindAllUseCase
import team.msg.sms.domain.student.usecase.SignUpUseCase
import javax.validation.Valid

@RestController
@RequestMapping("/student")
class StudentWebAdapter(
    private val signUpUseCase: SignUpUseCase,
    private val findAllUseCase: FindAllUseCase
) {
    @GetMapping
    fun findAll(
        @RequestParam(name = "page") page: Int,
        @RequestParam(name = "size") size: Int,
        filterRequest: FindAllFiltersWebRequest
    ): ResponseEntity<StudentInfoListResponse> =
        findAllUseCase.execute(page, size, filterRequest.toData())
            .let { ResponseEntity.ok(it) }

    @PostMapping
    fun signUpStudent(@RequestBody @Valid signUpWebRequest: SignUpWebRequest): ResponseEntity<Void> =
    signUpUseCase.execute(signUpWebRequest.toData())
            .run { ResponseEntity.ok().build() }
}