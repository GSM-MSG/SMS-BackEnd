package team.msg.sms.domain.teacher

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.msg.sms.domain.teacher.dto.req.SignUpHomeroomTeacherWebRequest
import team.msg.sms.domain.teacher.usecase.*
import javax.validation.Valid

@RestController
@RequestMapping("/teacher")
class TeacherWebAdapter(
    private val signUpTeacherUseCase: SignUpTeacherUseCase,
    private val signUpDirectorTeacherUseCase: SignUpDirectorTeacherUseCase,
    private val signUpDeputyPrincipalTeacherUseCase: SignUpDeputyPrincipalTeacherUseCase,
    private val signUpPrincipalTeacherUseCase: SignUpPrincipalTeacherUseCase,
    private val signUpHomeroomTeacherUseCase: SignUpHomeroomTeacherUseCase
) {
    @PostMapping("/common")
    fun signUpTeacher(): ResponseEntity<Unit> =
        signUpTeacherUseCase.execute()
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }

    @PostMapping("/director")
    fun signUpDirectorTeacher(): ResponseEntity<Unit> =
        signUpDirectorTeacherUseCase.execute()
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }

    @PostMapping("/deputy-principal")
    fun signUpDeputyPrincipalTeacher(): ResponseEntity<Unit> =
        signUpDeputyPrincipalTeacherUseCase.execute()
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }

    @PostMapping("/principal")
    fun signUpPrincipalTeacher(): ResponseEntity<Unit> =
        signUpPrincipalTeacherUseCase.execute()
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }

    @PostMapping("/homeroom")
    fun signUpHomeroomTeacher(@RequestBody @Valid signUpHomeroomTeacherWebRequest: SignUpHomeroomTeacherWebRequest): ResponseEntity<Unit> =
        signUpHomeroomTeacherUseCase.execute(signUpHomeroomTeacherWebRequest.toData())
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }
}
