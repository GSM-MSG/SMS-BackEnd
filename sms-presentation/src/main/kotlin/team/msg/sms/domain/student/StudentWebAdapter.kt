package team.msg.sms.domain.student

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import team.msg.sms.domain.student.dto.SignUpWebRequest
import team.msg.sms.domain.student.dto.response.DetailStudentInfoAnonymousResponse
import team.msg.sms.domain.student.dto.response.DetailStudentInfoResponse
import team.msg.sms.domain.student.dto.response.StudentInfoListResponse
import team.msg.sms.domain.student.usecase.FindAllUseCase
import team.msg.sms.domain.student.usecase.SignUpUseCase
import team.msg.sms.domain.student.usecase.StudentInfoAnonymousUseCase
import team.msg.sms.domain.student.usecase.StudentInfoDetailUseCase
import javax.validation.Valid

@RestController
@RequestMapping("/student")
class StudentWebAdapter(
    private val signUpUseCase: SignUpUseCase,
    private val findAllUseCase: FindAllUseCase,
    private val studentInfoAnonymousUseCase: StudentInfoAnonymousUseCase,
    private val studentInfoDetailUseCase: StudentInfoDetailUseCase
) {
    @GetMapping
    fun findAll(
        @RequestParam(name = "page") page: Int,
        @RequestParam(name = "size") size: Int,
//        filterRequest: FindAllFiltersWebRequest
    ): ResponseEntity<StudentInfoListResponse> =
        findAllUseCase.execute(page, size)
            .let { ResponseEntity.ok(it) }

    @PostMapping
    fun signUpStudent(@RequestBody @Valid signUpWebRequest: SignUpWebRequest): ResponseEntity<Void> =
        signUpUseCase.execute(signUpWebRequest.toData())
            .run { ResponseEntity.ok().build() }

    @GetMapping("/anonymous/{uuid}")
    fun findForAnonymousRole(@PathVariable uuid: String): ResponseEntity<DetailStudentInfoAnonymousResponse> =
        studentInfoAnonymousUseCase.execute(uuid)
            .let { ResponseEntity.ok(it) }

    @GetMapping("/{uuid}")
    fun findForStudentRole(@PathVariable uuid: String): ResponseEntity<DetailStudentInfoResponse> =
        studentInfoDetailUseCase.execute(uuid)
            .let { ResponseEntity.ok(it) }
}