package team.msg.sms.domain.teacher

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.msg.sms.domain.teacher.usecase.SignUpTeacherUseCase

@RestController
@RequestMapping("/teacher")
class TeacherWebAdapter(
    private val signUpUseCase: SignUpTeacherUseCase
) {
    @PostMapping("/common")
    fun signUpTeacher(): ResponseEntity<Void> =
        signUpUseCase.execute()
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }
}
